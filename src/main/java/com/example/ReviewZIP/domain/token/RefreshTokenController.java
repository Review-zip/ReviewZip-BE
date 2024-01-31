package com.example.ReviewZIP.domain.token;

import com.example.ReviewZIP.domain.token.dto.LoginRequestDto;
import com.example.ReviewZIP.domain.token.dto.SignUpRequestDto;
import com.example.ReviewZIP.domain.token.dto.SignUpResponseDto;
import com.example.ReviewZIP.domain.token.dto.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/local/sign-up")
    @Operation(summary = "회원가입", description = "회원가입")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto savedUserDto = refreshTokenService.signUp(signUpRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUserDto);
    }

    @PostMapping("/local/login")
    @Operation(summary = "로그인", description = "로그인")
    public ResponseEntity<TokenDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(refreshTokenService.login(loginRequestDto));
    }
}
