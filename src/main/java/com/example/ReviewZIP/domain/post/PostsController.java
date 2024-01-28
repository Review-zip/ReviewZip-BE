package com.example.ReviewZIP.domain.post;

import com.example.ReviewZIP.domain.post.dto.request.PostRequestDto;
import com.example.ReviewZIP.domain.post.dto.response.PostResponseDto;
import com.example.ReviewZIP.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.ReviewZIP.domain.post.dto.request.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostsController {
    private final PostsService postsService;

    // 특정 게시글의 정보 가져오기
    @GetMapping("/{postId}")
    @Operation(summary = "특정 게시글의 정보 가져오기 API",description = "게시글의 id를 이용하여 상세정보 출력, UserInfoDto & ImageListDto & PostInfoDto 이용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "POST401", description = "해당 게시물이 존재하지 않음",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "postId", description = "게시글의 아이디"),
            @Parameter(name = "page", description = "페이지 번호"),
            @Parameter(name = "size", description = "페이징 사이즈")
    })
    public ApiResponse<PostResponseDto.PostInfoDto> getPostInfo(@PathVariable(name = "postId") Long postId){

        PostResponseDto.PostInfoDto postInfoDto = postsService.getPostInfoDto(postId);

        return ApiResponse.onSuccess(postInfoDto);
    }

    @PostMapping
    @Operation(summary = "게시글 생성", description = "PostRequestDto, CreatedPostResponseDto 사용")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "POST403", description = "게시글 작성 실패",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<PostResponseDto.CreatedPostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto){
        Posts post = postsService.createPost(postRequestDto);
        return ApiResponse.onSuccess(PostsConverter.toPostResponseDto(post));
    }

    @GetMapping("/random")
    @Operation(summary = "랜덤으로 게시글 3개 가져오기", description = "PostInfoDto")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "POST405", description = "랜덤으로 게시글 3개 가져오기 실패",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<List<PostResponseDto.PostInfoDto>> getRandomPosts(@RequestParam Long userId) {
        List<PostResponseDto.PostInfoDto> randomPostInfoDtos = postsService.getRandomPostInfoDto(userId);

        return ApiResponse.onSuccess(randomPostInfoDtos);
    }
}