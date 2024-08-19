package com.example.techtask.service.repository;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = "SELECT u.* " +
            "FROM users u " +
            "JOIN orders o ON u.id = o.user_id " +
            "WHERE EXTRACT(YEAR FROM o.created_at) = 2003 " +
            "GROUP BY u.id " +
            "ORDER BY SUM(o.price * o.quantity) DESC LIMIT 1;")
    Optional<User> findUserWithMaxDeliveredItemsIn2003();

    @Query(nativeQuery = true, value = "SELECT DISTINCT u.* " +
            "FROM users u JOIN orders o ON u.id = o.user_id " +
            "WHERE o.order_status = 'PAID' " +
            "AND EXTRACT(YEAR FROM o.created_at) = 2010;")
    List<User> findUsersWithPaidOrdersIn2010();
}
