package com.example.ReviewZIP.domain.store;

import com.example.ReviewZIP.domain.store.dto.response.StoreResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class StoresConverter {

    public static StoreResponseDto.StoreDto toStoreDto(Stores store) {
        return StoreResponseDto.StoreDto.builder()
                .id(store.getId())
                .name(store.getName())
                .postId(store.getPost().getId())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .address(store.getAddress())
                .createdAt(store.getCreatedAt())
                .updatedAt(store.getUpdatedAt())
                .build();
    }

    public static StoreResponseDto.StoreListDto toStoreListDto(Page<Stores> storeList) {
        List<StoreResponseDto.StoreDto> storeDTOList = storeList.stream()
                .map(StoresConverter::toStoreDto)
                .collect(Collectors.toList());

        return StoreResponseDto.StoreListDto.builder()
                .isLast(storeList.isLast())
                .isFirst(storeList.isFirst())
                .totalPage(storeList.getTotalPages())
                .totalElements(storeList.getTotalElements())
                .listSize(storeDTOList.size())
                .storeList(storeDTOList)
                .build();
    }
}
