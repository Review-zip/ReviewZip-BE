package com.example.ReviewZIP.domain.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Long> {

    Page<Stores> findByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude, Pageable pageable);
}
