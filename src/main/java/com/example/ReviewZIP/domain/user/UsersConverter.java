package com.example.ReviewZIP.domain.user;

import com.example.ReviewZIP.domain.follow.Follows;
import com.example.ReviewZIP.domain.postHashtag.PostHashtags;
import com.example.ReviewZIP.domain.searchHistory.SearchHistories;
import com.example.ReviewZIP.domain.searchHistory.SearchType;
import com.example.ReviewZIP.domain.token.dto.request.SignUpRequestDto;
import com.example.ReviewZIP.domain.user.dto.response.UserResponseDto;
import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.handler.SearchHandler;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.ReviewZIP.domain.postHashtag.PostHashtagsConverter.postHashtagsRepository;

public class UsersConverter {


    public static UserResponseDto.UserPreviewDto toUserPreviewDto(Users user, List<Long> followingIdList) {
        boolean following = followingIdList.contains(user.getId());
        return UserResponseDto.UserPreviewDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .following(following)
                .build();
    }

    public static List<UserResponseDto.UserPreviewDto> toUserPreviewListDto(List<Users> userList, List<Long> followingIdList) {
        return userList.stream()
                .map(user -> toUserPreviewDto(user, followingIdList))
                .collect(Collectors.toList());


    }

    // 팔로잉 목록 converter
    public static UserResponseDto.UserPreviewDto toFollowingPreviewDto(Follows follows, List<Long> followingIdList){
        boolean isFollowing = followingIdList.contains(follows.getReceiver().getId());
        return UserResponseDto.UserPreviewDto.builder()
                .userId(follows.getReceiver().getId())
                .name(follows.getReceiver().getName())
                .profileUrl(follows.getReceiver().getProfileUrl())
                .nickname(follows.getReceiver().getNickname())
                .following(isFollowing)
                .build();
    }

    public static List<UserResponseDto.UserPreviewDto> toFollowingPreviewListDto(List<Follows> followsList, List<Long> followingIdList){
        return followsList.stream()
                .map(follow -> toFollowingPreviewDto(follow, followingIdList)).collect(Collectors.toList());

    }

    public static UserResponseDto.UserPreviewDto toFollowerPreviewDto(Follows follows, List<Long> followingIdList){
        boolean isFollowing = followingIdList.contains(follows.getSender().getId());
        return UserResponseDto.UserPreviewDto.builder()
                .userId(follows.getSender().getId())
                .name(follows.getSender().getName())
                .profileUrl(follows.getSender().getProfileUrl())
                .nickname(follows.getSender().getNickname())
                .following(isFollowing)
                .build();
    }

    public static List<UserResponseDto.UserPreviewDto> toFollowerPreviewListDto(List<Follows> followsList, List<Long> followingIdList){
        return followsList.stream()
                .map(follow -> toFollowerPreviewDto(follow, followingIdList)).collect(Collectors.toList());

    }

    public static UserResponseDto.UserInfoDto toUserInfoDto(Users user){
        return UserResponseDto.UserInfoDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileUrl(user.getProfileUrl())
                .postNum(user.getPostList().size())
                .followingNum(user.getFollowingList().size())
                .followerNum(user.getFollowerList().size())
                .build();
    }
    public static UserResponseDto.OtherUserInfoDto toOtherInfoDto(Users user, boolean isFollowing){

        String imageUrl = (user.getProfileUrl() != null) ? user.getProfileUrl() : null;

        return UserResponseDto.OtherUserInfoDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .nickname(user.getNickname())
                .profileUrl(imageUrl)
                .postNum(user.getPostList().size())
                .followingNum(user.getFollowingList().size())
                .followerNum(user.getFollowerList().size())
                .following(isFollowing)
                .build();
    }

    public static UserResponseDto.PostHashtagsPreviewDto toHashtagPreviewDto(String hashtag){
        List<PostHashtags> postHashtagsRepositoryList = postHashtagsRepository.findAllByHashtag(hashtag);
        int lastIndex = postHashtagsRepositoryList.size() - 1;
        PostHashtags latestHashtag = postHashtagsRepositoryList.get(lastIndex);
        return UserResponseDto.PostHashtagsPreviewDto.builder()
                .hashtagId(latestHashtag.getId())
                .tagName(hashtag)
                .postNum(postHashtagsRepositoryList.size())
                .build();
    }

    public static  UserResponseDto.HistoryDto toHistoryDto(SearchHistories history, List<Long> followingIdList){
        if(history.getType().equals(SearchType.USER)){
            return UserResponseDto.HistoryDto.builder()
                    .historyId(history.getId())
                    .user(toUserPreviewDto(history.getObject(), followingIdList))
                    .hashtag(null)
                    .type("USER")
                    .build();

        } else if (history.getType().equals(SearchType.HASHTAG)){
            return UserResponseDto.HistoryDto.builder()
                    .historyId(history.getId())
                    .user(null)
                    .hashtag(toHashtagPreviewDto(history.getHashtag()))
                    .type("HASHTAG")
                    .build();
        } else{
            throw new SearchHandler(ErrorStatus.HISTORY_TYPE_NOT_VALID);
        }
    }

    public static List<UserResponseDto.HistoryDto> toHistoryDtoList(List<SearchHistories> historyList, List<Long> followingIdList){
        return historyList.stream()
                .map(history->toHistoryDto(history, followingIdList))
                .collect(Collectors.toList());
    }

    public static Users toSignUpDto(SignUpRequestDto signUpRequestDto) {
        return Users.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getEmail())
                .password(signUpRequestDto.getPassword())
                .nickname(signUpRequestDto.getNickname())
                .phoneNum(signUpRequestDto.getPhoneNum())
                .profileUrl("https://reviewzipbucket.s3.ap-northeast-2.amazonaws.com/ReviewImage/911a02f0-206c-4fb0-b287-f49b58429526.png")
                .status(Status.ENABLED)
                .build();
    }
}
