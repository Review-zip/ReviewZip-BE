package com.example.ReviewZIP.domain.post;

import com.example.ReviewZIP.domain.image.Images;
import com.example.ReviewZIP.domain.postHashtag.PostHashtags;
import com.example.ReviewZIP.domain.postLike.PostLikes;
import com.example.ReviewZIP.domain.user.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false)
    private Double point;

    @Column(name = "is_read", nullable = false)
    private Boolean read;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Images> postImageList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostLikes> postLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostHashtags> postHashtagList = new ArrayList<>();

}
