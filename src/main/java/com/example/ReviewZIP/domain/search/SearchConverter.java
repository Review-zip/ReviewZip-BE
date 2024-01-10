package com.example.ReviewZIP.domain.search;

import com.example.ReviewZIP.domain.post.Post;
import com.example.ReviewZIP.domain.search.dto.response.PostResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class SearchConverter {

    public static PostResponseDTO.PostDTO convertToPostDTO(Post post) {
        return PostResponseDTO.PostDTO.builder()
                .id(post.getId())
                .user_id(post.getUserId())
                .imgUrl(post.getImgUrl())
                .comment(post.getComment())
                .point(post.getPoint())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostResponseDTO.PostListDTO convertToPostListDTO(Page<Post> postList) {
        List<PostResponseDTO.PostDTO> postDTOList = postList.stream()
                .map(SearchConverter::convertToPostDTO)
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
