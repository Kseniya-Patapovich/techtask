package com.example.techtask.repository;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(nativeQuery = true, value = """
            SELECT u.* 
            FROM users u
            JOIN orders o ON u.id = o.user_id 
            WHERE EXTRACT(YEAR FROM o.created_at) = :year 
            AND CAST(o.order_status AS varchar) = :orderStatus
            GROUP BY u.id 
            ORDER BY SUM(o.price * o.quantity) DESC LIMIT 1
            """)
    Optional<User> findUserWithMaxDeliveredItemsIn2003(int year, String orderStatus);

    @Query(nativeQuery = true, value = """
            SELECT DISTINCT u.*
            FROM users u JOIN orders o ON u.id = o.user_id
            WHERE CAST(o.order_status AS varchar) = :orderStatus
            AND EXTRACT(YEAR FROM o.created_at) = :year
            """)
    List<User> findUsersWithPaidOrdersIn2010(int year, String orderStatus);
}
