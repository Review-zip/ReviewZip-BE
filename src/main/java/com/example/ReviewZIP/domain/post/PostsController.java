package com.example.ReviewZIP.domain.post;

import com.example.ReviewZIP.domain.post.dto.response.PostResponseDTO;
import com.example.ReviewZIP.domain.postHashtag.PostHashtagsService;
import com.example.ReviewZIP.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostsController {

    private final PostHashtagsService postHashtagsService;

    @GetMapping("/search/hashtags")
    public ApiResponse<PostResponseDTO.PostListDTO> searchPostsByHashtagId(@RequestParam Long id, @RequestParam (defaultValue = "0") Integer page) {
        Page<Posts> postPage = postHashtagsService.findPostsByHashtagId(id, page);
        PostResponseDTO.PostListDTO postListDTO = PostsConverter.toPostListDTO(postPage);
        return ApiResponse.onSuccess(postListDTO);
    }
}
