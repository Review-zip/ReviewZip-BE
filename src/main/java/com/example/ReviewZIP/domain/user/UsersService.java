package com.example.ReviewZIP.domain.user;

import com.example.ReviewZIP.domain.follow.Follows;
import com.example.ReviewZIP.domain.follow.FollowsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {
    private final UsersRepository usersRepository;
    private final FollowsRepository followsRepository;
    Page<Follows> getOtherFollowerList(Long userId, Integer page, Integer size){
        Users receiver = usersRepository.findById(userId).orElseThrow(()->new IllegalArgumentException("NOT FOUND USER"));
        Page<Follows> FollowsPage = followsRepository.findAllByReceiver(receiver, PageRequest.of(page, size));

        return FollowsPage;
    }
}
