package com.example.ReviewZIP.domain.postHashtag;

import com.example.ReviewZIP.domain.post.Posts;
import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.handler.PostHashtagsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.ReviewZIP.domain.postHashtag.PostHashtags;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostHashtagsService {

    private final PostHashtagsRepository postHashtagRepository;

    public List<PostHashtags> searchHashtagsById(Long id) {
        String hashtagsName = postHashtagRepository.findById(id).orElseThrow(() -> new PostHashtagsHandler(ErrorStatus.HASHTAG_NOT_FOUND)).getHashtag();
        List<PostHashtags> hashtagsList = postHashtagRepository.findByNameContaining(hashtagsName);
        return hashtagsList;
    }
}
