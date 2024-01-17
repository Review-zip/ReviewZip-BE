package com.example.ReviewZIP.domain.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class StoresService {

    private final StoresRepository storeRepository;


    public Page<Stores> getStoresByLocation(BigDecimal latitude, BigDecimal longitude, Integer page) {
        return storeRepository.findByLatitudeAndLongitude(latitude, longitude, PageRequest.of(page, 10));
    }
}

