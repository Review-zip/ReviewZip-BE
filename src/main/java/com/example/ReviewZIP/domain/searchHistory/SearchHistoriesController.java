package com.example.ReviewZIP.domain.searchHistory;

import com.example.ReviewZIP.domain.searchHistory.dto.request.SearchHistoryRequestDto;
import com.example.ReviewZIP.global.response.ApiResponse;
import com.example.ReviewZIP.global.response.code.resultCode.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/histories")
public class SearchHistoriesController {

    private final SearchHistoriesService searchHistoriesService;

    @PostMapping("/")
    @Operation(summary = "검색 기록 저장 API", description = "SearchHistoriesRequestDto 사용, type 입력 시 1 -> NAME, 2 -> NICKNAME, 3 -> HASHTAG")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "HISTORY401", description = "검색 기록 저장 실패",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> createSearchHistories(@RequestBody SearchHistoryRequestDto searchHistoryDto) {
        searchHistoriesService.createSearchHistories(searchHistoryDto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}
