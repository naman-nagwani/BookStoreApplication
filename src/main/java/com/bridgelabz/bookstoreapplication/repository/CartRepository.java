package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<CartData,Integer> {
    @Query(nativeQuery = true,value = "select * from cart_data where user_id like :userId")
    List<CartData> getCartDataByUserId(int userId);
}
