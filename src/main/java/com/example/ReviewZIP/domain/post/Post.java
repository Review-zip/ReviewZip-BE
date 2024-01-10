package com.example.ReviewZIP.domain.post;


import com.example.ReviewZIP.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "img_url", length = 50)
    private String imgUrl;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "point")
    private Double point;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Long getUserId() {
        return user != null ? user.getId() : null;
    }
}