package com.example.ReviewZIP.domain.store;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoresService {

    private final StoresRepository storeRepository;

    public Page<Stores> getStoresByName(String name, Integer page) {
        return storeRepository.findByName(name, PageRequest.of(page, 10));
    }
}
