package com.example.ReviewZIP.domain.sms;

import com.example.ReviewZIP.domain.sms.dto.request.SmsDto;
import com.example.ReviewZIP.domain.user.Users;
import com.example.ReviewZIP.domain.user.UsersRepository;
import com.example.ReviewZIP.global.config.SmsCertificationConfig;
import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.handler.SmsHandler;
import com.example.ReviewZIP.global.response.exception.handler.UsersHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SMSService {

    private final SmsCertificationConfig smsCertificationConfig;
    private final SmsRepository smsRepository;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    private static final int RANDOM_NUM_MULTIPLIER = 9000;
    private static final int RANDOM_NUM_BASE = 1000;

    public void sendSms(SmsDto.PasswordResetRequestDto requestDto) {
        String receiverNumber = requestDto.getPhoneNum();
        int randomNum = (int) (Math.random() * RANDOM_NUM_MULTIPLIER) + RANDOM_NUM_BASE;
        String certificationNum = String.valueOf(randomNum);
        smsCertificationConfig.sendSms(receiverNumber, certificationNum);
        smsRepository.createSmsCertification(receiverNumber, certificationNum);
    }

    public void verifySms(SmsDto.PasswordResetRequestDto requestDto) {
        if (isVerify(requestDto)) {
            throw new SmsHandler(ErrorStatus.SMS_VERIFICATION_NUMBER_MISMATCH);
        }
        smsRepository.removeSmsCertification(requestDto.getPhoneNum());
    }

    public boolean isVerify(SmsDto.PasswordResetRequestDto requestDto) {
        return !(smsRepository.hasKey(requestDto.getPhoneNum()) &&
                smsRepository.getSmsCertification(requestDto.getPhoneNum())
                .equals(requestDto.getCertificationNum()));
    }

    public void resetPassword(SmsDto.PasswordResetRequestDto requestDto) {
        if(!isVerify(requestDto)) {
            throw new SmsHandler(ErrorStatus.SMS_VERIFICATION_NUMBER_MISMATCH);
        }
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        Users user = usersRepository.findByPhoneNum(requestDto.getPhoneNum())
                .orElseThrow(() -> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        user.setPassword(encodedPassword);
        usersRepository.save(user);

        smsRepository.removeSmsCertification(requestDto.getPhoneNum()); // 인증번호 삭제
    }
}
