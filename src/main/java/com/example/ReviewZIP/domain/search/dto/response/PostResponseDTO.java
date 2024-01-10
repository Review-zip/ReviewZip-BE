package com.example.ReviewZIP.domain.search.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PostResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostListDTO {
        List<PostDTO> postList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PostDTO {
        @NotNull
        private Long id;

        private Long user_id;

        @NotBlank
        private String imgUrl;

        @NotBlank
        private String comment;

        @NotNull
        private Double point;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;
    }
}