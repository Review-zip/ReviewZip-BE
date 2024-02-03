package com.example.ReviewZIP.domain.searchHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchHistoriesRepository extends JpaRepository<SearchHistories, Long> {
}
