package com.example.ReviewZIP.domain.store;

import com.example.ReviewZIP.domain.store.dto.response.StoreResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class StoresConverter {

    public static StoreResponseDTO.StoreDTO toStoreDTO(Stores store) {
        return StoreResponseDTO.StoreDTO.builder()
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

    public static StoreResponseDTO.StoreListDTO toStoreListDTO(Page<Stores> storeList) {
        List<StoreResponseDTO.StoreDTO> storeDTOList = storeList.stream()
                .map(StoresConverter::toStoreDTO)
                .collect(Collectors.toList());

        return StoreResponseDTO.StoreListDTO.builder()
                .isLast(storeList.isLast())
                .isFirst(storeList.isFirst())
                .totalPage(storeList.getTotalPages())
                .totalElements(storeList.getTotalElements())
                .listSize(storeDTOList.size())
                .storeList(storeDTOList)
                .build();
    }
}
