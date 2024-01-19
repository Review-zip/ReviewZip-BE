package com.example.ReviewZIP.domain.post;

import com.example.ReviewZIP.domain.post.dto.response.PostResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PostsConverter {

    public static PostResponseDto.PostDTO toPostDTO(Posts post) {
        return PostResponseDto.PostDTO.builder()
                .id(post.getId())
                .comment(post.getComment())
                .point(post.getPoint())
                .flag(post.getIs_read())
                .userId(post.getUser().getId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDto.PostListDTO toPostListDTO(Page<Posts> postList) {
        List<PostResponseDto.PostDTO> postDTOList = postList.stream()
                .map(PostsConverter::toPostDTO)
                .collect(Collectors.toList());

        return PostResponseDto.PostListDTO.builder()
                .isLast(postList.isLast())
                .isFirst(postList.isFirst())
                .totalPage(postList.getTotalPages())
                .totalElements(postList.getTotalElements())
                .listSize(postDTOList.size())
                .postList(postDTOList)
                .build();
    }
}
