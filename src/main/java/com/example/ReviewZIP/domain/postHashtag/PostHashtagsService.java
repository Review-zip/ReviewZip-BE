package com.example.ReviewZIP.domain.postHashtag;

import com.example.ReviewZIP.domain.post.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.ReviewZIP.domain.postHashtag.PostHashtags;

@Service
@RequiredArgsConstructor
public class PostHashtagsService {

    private final PostHashtagsRepository postHashtagRepository;

    public Page<Posts> findPostsByHashtagId(Long hashtagId, int page) {

        Page<PostHashtags> postHashtags = postHashtagRepository.findById(hashtagId, PageRequest.of(page, 10));

        return postHashtags.map(PostHashtags::getPost);
    }
}
