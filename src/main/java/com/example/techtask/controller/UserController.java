package com.example.techtask.controller;

import com.example.techtask.model.User;
import com.example.techtask.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Attention! Only DI and service interaction applicable in this class. Each controller method
 * should only contain a call to the corresponding service method
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    // DI here
    private final UserServiceImpl userService;

    @GetMapping("desired-user")
    @ResponseStatus(HttpStatus.OK)
    public User findUser() {
        return userService.findUser();
    }

    @GetMapping("desired-users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findUsers() {
        return userService.findUsers();
    }
}
