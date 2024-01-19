package com.example.ReviewZIP.domain.postHashtag;

import com.example.ReviewZIP.domain.post.Posts;
import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.handler.PostHashtagsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.ReviewZIP.domain.postHashtag.PostHashtags;

@Service
@RequiredArgsConstructor
public class PostHashtagsService {

    private final PostHashtagsRepository postHashtagRepository;

    public Page<Posts> findPostsByHashtagId(Long hashtagId, int page) throws PostHashtagsHandler {
        Page<PostHashtags> postHashtags = postHashtagRepository.findById(hashtagId, PageRequest.of(page, 10));

        if (postHashtags.isEmpty()) {
            throw new PostHashtagsHandler(ErrorStatus.HASHTAG_NOT_FOUND);
        }

        return postHashtags.map(PostHashtags::getPost);
    }
}
