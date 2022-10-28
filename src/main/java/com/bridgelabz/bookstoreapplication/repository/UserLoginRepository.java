package com.bridgelabz.bookstoreapplication.repository;

import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLoginData,Integer> {
    @Query(value = "select * from user_login where email = :email",nativeQuery = true)
    UserLoginData findUserLoginDataByEMail(String email);
}