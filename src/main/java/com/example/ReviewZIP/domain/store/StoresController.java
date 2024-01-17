package com.example.ReviewZIP.domain.store;

import com.example.ReviewZIP.domain.store.dto.response.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stores")
public class StoresController {

    private final StoresService storesService;

    @GetMapping("/search/name")
    public ResponseEntity<StoreResponseDTO.StoreListDTO> searchStoresByStoreName(@RequestParam String name, @RequestParam (defaultValue = "0") Integer page) {
        Page<Stores> storePage = storesService.getStoresByName(name, page);
        StoreResponseDTO.StoreListDTO storeListDTO = StoresConverter.toStoreListDTO(storePage);
        return ResponseEntity.ok(storeListDTO);
    }
}
