package com.example.ReviewZIP.domain.hashtag;

import com.example.ReviewZIP.domain.post.Post;
import org.springframework.data.domain.Page;

public interface HashtagService {

    Page<Post> findPostsByHashtagId(Long id, int page);
}
