package com.example.ReviewZIP.domain.searchHistory;

import com.example.ReviewZIP.domain.searchHistory.dto.request.SearchHistoryRequestDto;

public class SearchHistoriesConverter {

    public static SearchHistories toSearchHistories (SearchHistoryRequestDto request) {

        SearchType searchType = switch (request.getType()) {
            case 1 -> SearchType.NAME;
            case 2 -> SearchType.NICKNAME;
            case 3 -> SearchType.HASHTAG;
            default -> null;
        };

        return SearchHistories.builder()
                .content(request.getContent())
                .type(searchType)
                .build();
    }
}
