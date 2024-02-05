package com.example.ReviewZIP.domain.searchHistory;

import com.example.ReviewZIP.global.response.ApiResponse;
import com.example.ReviewZIP.global.response.code.resultCode.SuccessStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/history")
public class SearchHistoriesController {
    private final SearchHistoriesService searchHistoriesService;
    @PostMapping("/users/{userId}")
    @GetMapping("/search/name")
    @Operation(summary = "유저 검색 저장하기 API",description = "유저의 id를 받아 해당 유저 검색 기록을 저장")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER408", description = "유저를 찾을 수 없음",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디"),
    })
    public ApiResponse<SuccessStatus> saveUserSearchHistory(@PathVariable(name = "userId")Long userId){
        // 나는 1L로 설정
        searchHistoriesService.saveUserSearchHistory(1L, userId);

        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @PostMapping("/hashtags")
    @Operation(summary = "해시태그 검색 저장하기 API",description = "해시태그의 값을 받아 저장")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "hashtag", description = "해시태그"),
    })
    public ApiResponse<SuccessStatus> saveHashtagSearchHistory(@RequestParam(name = "hashtag")String hashtag){
        // 나는 1L로 설정
        searchHistoriesService.saveHashtagSearchHistory(1L, hashtag);

        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @DeleteMapping("/{userId}")
    @Operation(summary = "유저 검색 기록 삭제하기 API",description = "유저의 id를 받아 해당 검색기록을 삭제")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER408", description = "유저 검색 삭제할 유저가 이미 존재하지 않음",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "HISTORY401", description = "해당하는 유저 검색기록이 존재하지 않음",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디"),
    })
    public  ApiResponse<SuccessStatus> deleteUserSearchHistory(@PathVariable(name = "userId")Long userId){
        // 나를 1L로 가정
        searchHistoriesService.deleteUserSearchHistory(1L,userId);

        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @DeleteMapping("/hashtags")
    @Operation(summary = "해시태그 검색 기록 삭제하기 API",description = "해시태그를 parameter로 받아 검색 기록 삭제")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "HISTORY401", description = "해당하는 해시태그 검색기록이 존재하지 않음",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "hashtag", description = "해시태그"),
    })
    public  ApiResponse<SuccessStatus> deleteHashtagSearchHistory(@RequestParam(name = "hashtag")String hashtag){
        // 나를 1L로 설정
        searchHistoriesService.deleteHashtagSearchHistory(1L, hashtag);

        return ApiResponse.onSuccess(SuccessStatus._OK);
    }
}