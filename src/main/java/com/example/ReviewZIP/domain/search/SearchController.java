package com.example.ReviewZIP.domain.search;

import com.example.ReviewZIP.domain.hashtag.HashtagService;
import com.example.ReviewZIP.domain.post.Post;
import com.example.ReviewZIP.domain.search.dto.response.PostResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/searches")
public class SearchController{

    private final HashtagService hashtagService;

    @GetMapping("/hashtags")
    public ResponseEntity<PostResponseDTO.PostListDTO> searchPostsByHashtagId(@RequestParam Long id, @RequestParam (defaultValue = "0") Integer page) {
        Page<Post> postPage = hashtagService.findPostsByHashtagId(id, page);
        PostResponseDTO.PostListDTO postListDTO = SearchConverter.convertToPostListDTO(postPage);
        return ResponseEntity.ok(postListDTO);
    }

}