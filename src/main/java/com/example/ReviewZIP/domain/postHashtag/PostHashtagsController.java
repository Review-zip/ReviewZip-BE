package com.example.ReviewZIP.domain.postHashtag;


import com.example.ReviewZIP.domain.postHashtag.dto.response.PostHashtagResponseDto;
import com.example.ReviewZIP.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hashtags")
public class PostHashtagsController {

    private final PostHashtagsService postHashtagsService;

    @GetMapping("/{hashtagId}")
    public ApiResponse<PostHashtagResponseDto.PostHashtagsPreviewListDto> searchHashtagsById (@PathVariable Long hashtagId) {
        List<PostHashtags> postHashtagsList = postHashtagsService.searchHashtagsById(hashtagId);
        PostHashtagResponseDto.PostHashtagsPreviewListDto postHashtagsPreviewListDto = PostHashtagsConverter.toPostHashtagsPreviewListDto(postHashtagsList);
        return ApiResponse.onSuccess(postHashtagsPreviewListDto);
    }

}
