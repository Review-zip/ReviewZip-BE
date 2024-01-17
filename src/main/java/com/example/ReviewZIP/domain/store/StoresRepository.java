package com.example.ReviewZIP.domain.store;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoresRepository extends JpaRepository<Stores, Long> {
    Page<Stores> findByName(String name, Pageable pageable);
}
