package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.model.enumiration.UserStatus;
import com.example.techtask.service.OrderService;
import com.example.techtask.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order findOrder() {
        return orderRepository.findNewestOrderWithMoreThanOneItem(1)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findOrdersFromActiveUsersOrderedByCreationDate(UserStatus.ACTIVE.name());
    }
}
