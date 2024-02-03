package com.example.ReviewZIP.domain.searchHistory.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryRequestDto {
        private String content;
        private Integer type;
}
