package com.example.ReviewZIP.domain.userStores;


import com.example.ReviewZIP.domain.userStores.dto.request.UserStoresRequestDto;
import com.example.ReviewZIP.domain.userStores.dto.response.UserStoresResponseDto;
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
@RequestMapping("/v1/user/stores")
public class UserStoresController {

    private final UserStoresService userStoresService;

    @PostMapping("/")
    @Operation(summary = "특정 장소를 관심 장소로 등록 API",description = "특정 장소를 유저의 관심 장소로 등록한다, 입력 시 CreateUserStoresDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<SuccessStatus> createUserStores(@RequestBody UserStoresRequestDto.CreateUserStoresDto dto) {
        userStoresService.createUserStores(dto);
        return ApiResponse.onSuccess(SuccessStatus._OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "특정 유저의 관심 장소 목록 API",description = "특정 특정 유저의 관심 장소 목록을 가져온다, 반환 시 StoreInfoListDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "유저의 아이디"),
    })
    public ApiResponse<UserStoresResponseDto.StoreInfoListDto> getStoresByUser(@PathVariable Long userId) {
        return ApiResponse.onSuccess(userStoresService.getStoreInfo(userId));
    }

    @GetMapping("/location")
    @Operation(summary = "특정 위치의 장소가 관심 장소 인지 판별하는 API",description = "특정 위치의 위도 경도 값을 바탕으로 관심 장소인지 판별하는 기능")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "USER404", description = "유저가 존재하지 않습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "lat", description = "장소의 위도"),
            @Parameter(name = "lon", description = "장소의 경도"),

    })
    public ApiResponse<Boolean> isInterestPlace(@RequestParam Double lat, @RequestParam Double lon) {
        return ApiResponse.onSuccess(userStoresService.isInterestPlace(lat,lon));
    }

}
