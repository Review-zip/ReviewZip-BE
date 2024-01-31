package com.example.ReviewZIP.domain.token;

import com.example.ReviewZIP.domain.token.dto.LoginRequestDto;
import com.example.ReviewZIP.domain.token.dto.SignUpRequestDto;
import com.example.ReviewZIP.domain.token.dto.SignUpResponseDto;
import com.example.ReviewZIP.domain.token.dto.TokenDto;
import com.example.ReviewZIP.domain.user.Users;
import com.example.ReviewZIP.domain.user.UsersRepository;
import com.example.ReviewZIP.global.config.jwt.JwtProvider;
import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        if(usersRepository.existsByEmail(signUpRequestDto.getEmail())) {
            throw new GeneralException(ErrorStatus.USER_EXISTS_EMAIL);
        }

        signUpRequestDto.setPassword(encodePassword(signUpRequestDto.getPassword()));

        return SignUpResponseDto.toDto(usersRepository.save(Users.toEntity(signUpRequestDto)));
    }

    public String encodePassword(String password) {

        return bCryptPasswordEncoder.encode(password);
    }

    @Transactional
    public TokenDto login(LoginRequestDto loginRequestDto) {
        // 1. Login ID/PW를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        // 2. authentication 메서드가 실행이 될 때, UserDetailsServiceImpl 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtProvider.generateToken(authentication);
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }

}
