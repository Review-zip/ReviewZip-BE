package com.example.ReviewZIP.domain.token.dto;

import com.example.ReviewZIP.domain.user.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponseDto {

    private String nickname;

    public static SignUpResponseDto toDto(Users user) {
        return SignUpResponseDto.builder()
                .nickname(user.getNickname())
                .build();
    }
}
