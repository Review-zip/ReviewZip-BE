package com.example.ReviewZIP.domain.store;

import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.handler.StoresHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoresService {

    private final StoresRepository storeRepository;

    public Page<Stores> getStoresByName(String name, Integer page) throws StoresHandler {

        Page<Stores> pageStores = storeRepository.findByName(name, PageRequest.of(page, 10));

        if (pageStores.isEmpty()) {
            throw new StoresHandler(ErrorStatus.STORE_NOT_FOUND);
        }

        return pageStores;
    }
}
