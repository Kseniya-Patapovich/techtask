package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.model.enumiration.OrderStatus;
import com.example.techtask.repository.UserRepository;
import com.example.techtask.service.UserService;
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
        return userRepository.findUserWithMaxDeliveredItemsIn2003(2003, OrderStatus.DELIVERED.name()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findUsersWithPaidOrdersIn2010(2010, OrderStatus.PAID.name());
    }
}
