package com.example.ReviewZIP.domain.store.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class StoreResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreListDTO {
        List<StoreDTO> storeList;
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
    public static class StoreDTO {
        private Long id;

        @NotBlank
        private String name;

        @NotBlank
        private String address;

        @NotNull
        private BigDecimal longitude;

        @NotNull
        private BigDecimal latitude;

        private Long postId;

        private LocalDate createdAt;
        private LocalDate updatedAt;
    }
}