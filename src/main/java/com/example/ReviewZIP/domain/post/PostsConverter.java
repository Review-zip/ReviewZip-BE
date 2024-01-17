package com.example.ReviewZIP.domain.post;

import com.example.ReviewZIP.domain.post.dto.response.PostResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PostsConverter {

    public static PostResponseDTO.PostDTO toPostDTO(Posts post) {
        return PostResponseDTO.PostDTO.builder()
                .id(post.getId())
                .comment(post.getComment())
                .point(post.getPoint())
                .flag(post.getIs_read())
                .userId(post.getUser().getId())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDTO.PostListDTO toPostListDTO(Page<Posts> postList) {
        List<PostResponseDTO.PostDTO> postDTOList = postList.stream()
                .map(PostsConverter::toPostDTO)
                .collect(Collectors.toList());

        return PostResponseDTO.PostListDTO.builder()
                .isLast(postList.isLast())
                .isFirst(postList.isFirst())
                .totalPage(postList.getTotalPages())
                .totalElements(postList.getTotalElements())
                .listSize(postDTOList.size())
                .postList(postDTOList)
                .build();
    }
}
