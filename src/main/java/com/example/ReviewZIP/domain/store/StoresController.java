package com.example.ReviewZIP.domain.store;

import com.example.ReviewZIP.domain.store.dto.response.StoreResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class StoresController {

    private final StoresService storeService;

    @GetMapping("/search/location")
    public ResponseEntity<StoreResponseDTO.StoreListDTO> searchStoresByLocation(@RequestParam BigDecimal latitude, @RequestParam BigDecimal longitude, @RequestParam (defaultValue = "0") Integer page ){
        Page<Stores> storePage = storeService.getStoresByLocation(latitude, longitude, page);
        StoreResponseDTO.StoreListDTO storeListDTO = StoresConverter.toStoreListDTO(storePage);
        return ResponseEntity.ok(storeListDTO);
    }
}
