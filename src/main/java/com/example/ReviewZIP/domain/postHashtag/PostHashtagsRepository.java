package com.example.ReviewZIP.domain.postHashtag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHashtagsRepository extends JpaRepository<PostHashtags, Long> {

    Page<PostHashtags> findById(Long id, Pageable pageable);
}
