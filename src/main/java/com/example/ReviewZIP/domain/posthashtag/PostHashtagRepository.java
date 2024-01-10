package com.example.ReviewZIP.domain.posthashtag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {

    Page<PostHashtag> findByHashtagId(Long id, Pageable pageable);
}
