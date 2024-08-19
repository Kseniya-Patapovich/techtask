package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import com.example.techtask.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findUser() {
        return userRepository.findUserWithMaxDeliveredItemsIn2003().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findUsersWithPaidOrdersIn2010();
    }
}
