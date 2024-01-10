package com.example.ReviewZIP.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profile_image;

    @Column(nullable = false)
    private String user_id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone_num;

    @Column(nullable = false)
    private String nickname;

}