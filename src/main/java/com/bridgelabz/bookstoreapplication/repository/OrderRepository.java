package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderData,Integer> {
    @Query(value = "select * from order_data where cancel=0",nativeQuery = true)
    List<OrderData> getAllOrder();
    @Query(value = "select * from order_data where user_id=:userId",nativeQuery = true)
    List<OrderData> getAllOrderForUser(int userId);
}
