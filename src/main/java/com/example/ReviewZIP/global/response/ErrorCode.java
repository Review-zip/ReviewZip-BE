package com.example.ReviewZIP.global.response;


import lombok.AllArgsConstructor;
import lombok.Getter;

// Enum Naming Format : {주체}_{이유}
// Message format : 동사 명사형으로 마무리
@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    INPUT_INVALID_VALUE(400, "G002", "잘못된 입력"),

    // OAuth
    EXPIRED_JWT_EXCEPTION(401, "AUTH001", "기존 토큰이 만료되었습니다. 토큰을 재발급해주세요."),

    // User
    USERS_EXISTS_EMAIL(400, "U001", "중복된 이메일입니다."),
    USERS_EXISTS_NAME(400, "U002", "중복된 이름입니다."),
    FAILED_TO_PASSWORD(400, "U003", "비밀번호가 잘못되었습니다."),
    USER_NOT_FOUND(400, "U004", "유저를 찾을 수 없습니다."),
    FOLLOWER_LIST_NOT_FOUND(400, "U005", "팔로워 목록을 찾을 수 없습니다."),
    FOLLOWING_LIST_NOT_FOUND(400, "U006", "팔로잉 목록을 찾을 수 없습니다."),

    // Post
    POST_NOT_FOUND(400, "P001", "게시글을 찾을 수 없습니다."),
    LIKE_PERSON_NOT_FOUND(400, "P002", "공감 누른 사람을 찾을 수 없습니다."),

    // Search
    HASHTAG_NOT_FOUND(400, "S001", "존재하지 않는 해쉬태그입니다."),
    STORE_NOT_FOUND(400, "S002", "존재하지 않는 가게입니다."),
    NICKNAME_NOT_FOUND(400, "S003", "존재하지 않는 유저 닉네임입니다."),
    NAME_NOT_FOUND(400, "S004", "존재하지 않는 유저 이름입니다."),
    LOCATION_NOT_FOUND(400, "S005", "존재하지 않는 가게 위치입니다."),

    // Follow
    ID_NOT_FOUND(400, "F001", "유저 ID를 찾을 수 없습니다.");

    private final int status;
    private final String code;
    private final String message;
}
