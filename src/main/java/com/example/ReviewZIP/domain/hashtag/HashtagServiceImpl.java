package com.example.ReviewZIP.domain.hashtag;

import com.example.ReviewZIP.domain.post.Post;
import com.example.ReviewZIP.domain.posthashtag.PostHashtag;
import com.example.ReviewZIP.domain.posthashtag.PostHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService{

    private final PostHashtagRepository postHashtagRepository;

    public Page<Post> findPostsByHashtagId(Long hashtagId, int page) {

        Page<PostHashtag> postHashtags = postHashtagRepository.findByHashtagId(hashtagId, PageRequest.of(page, 10));

        return postHashtags.map(PostHashtag::getPost);
    }
}
