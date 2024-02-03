package com.example.ReviewZIP.domain.searchHistory;


import com.example.ReviewZIP.domain.searchHistory.dto.request.SearchHistoryRequestDto;
import com.example.ReviewZIP.domain.user.Users;
import com.example.ReviewZIP.domain.user.UsersRepository;
import com.example.ReviewZIP.global.response.code.resultCode.ErrorStatus;
import com.example.ReviewZIP.global.response.exception.handler.SearchHistoriesHandler;
import com.example.ReviewZIP.global.response.exception.handler.UsersHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchHistoriesService {

    private final UsersRepository usersRepository;
    private final SearchHistoriesRepository searchHistoriesRepository;

    @Transactional
    public void createSearchHistories (SearchHistoryRequestDto request) {
        SearchHistories searchHistories = SearchHistoriesConverter.toSearchHistories(request);

        // 유저 ID를 받아와 1L 대신 입력
        Users users = usersRepository.findById(1L).orElseThrow(()-> new UsersHandler(ErrorStatus.USER_NOT_FOUND));
        searchHistories.setUser(users);

        try {
            searchHistoriesRepository.save(searchHistories);
        } catch (Exception e) {
            throw new SearchHistoriesHandler(ErrorStatus.SEARCH_HISTORIES_CREATE_FAIL);
        }
    }
}
