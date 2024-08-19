package com.example.techtask.repository;

import com.example.techtask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(nativeQuery = true, value = """
            SELECT * FROM orders
            WHERE quantity > :quantity ORDER BY created_at DESC LIMIT 1
            """)
    Optional<Order> findNewestOrderWithMoreThanOneItem(int quantity);

    @Query(nativeQuery = true, value = """
            SELECT o.* FROM orders o JOIN users u ON o.user_id = u.id
            WHERE CAST(u.user_status AS varchar) = :userStatus
            ORDER BY o.created_at ASC
            """)
    List<Order> findOrdersFromActiveUsersOrderedByCreationDate(String userStatus);
}
