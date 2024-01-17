package com.example.ReviewZIP.domain.post;

import com.example.ReviewZIP.domain.hashtag.HashtagsService;
import com.example.ReviewZIP.domain.post.dto.response.PostResponseDTO;
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

    private final HashtagsService hashtagsService;

    @GetMapping("/search/hashtags")
    public ResponseEntity<PostResponseDTO.PostListDTO> searchPostsByHashtagId(@RequestParam Long hashTagId, @RequestParam (defaultValue = "0") Integer page) {
        Page<Posts> postPage = hashtagsService.findPostsByHashtagId(hashTagId, page);
        PostResponseDTO.PostListDTO postListDTO = PostsConverter.toPostListDTO(postPage);
        return ResponseEntity.ok(postListDTO);
    }
}
