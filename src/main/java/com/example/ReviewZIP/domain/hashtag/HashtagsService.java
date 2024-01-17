package com.example.ReviewZIP.domain.hashtag;

import com.example.ReviewZIP.domain.post.Posts;
import com.example.ReviewZIP.domain.posthashtag.PostHashtags;
import com.example.ReviewZIP.domain.posthashtag.PostHashtagsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HashtagsService{

    private final PostHashtagsRepository postHashtagRepository;

    public Page<Posts> findPostsByHashtagId(Long hashtagId, int page) {

        Page<PostHashtags> postHashtags = postHashtagRepository.findByHashtagId(hashtagId, PageRequest.of(page, 10));

        return postHashtags.map(PostHashtags::getPost);
    }
}
