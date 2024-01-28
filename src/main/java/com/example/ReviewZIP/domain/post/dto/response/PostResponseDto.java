package com.example.ReviewZIP.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


public class PostResponseDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoDto{
        private Long id;
        private String nickname;
        private String profileUrl;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class ImageListDto{
        private Long id;
        private String url;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatedPostResponseDto {
        private Long postId;
        private Long userId;
        private String comment;
        private Double point;
        private List<Long> imageIds;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostInfoDto{
        private Long postId;
        private String comment;
        private Double point;
        private Integer likeNum;
        private boolean checkLike;
        private boolean checkScrab;
        private LocalDate createdAt;
        private UserInfoDto userInfo;
        private List<String> hashtags;
        private List<ImageListDto> postImages;
    }
}