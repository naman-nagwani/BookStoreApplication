package com.bridgelabz.bookstoreapplication.entity;

import com.bridgelabz.bookstoreapplication.DTO.UserLoginDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_login")
public class UserLoginData {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String eMail;

    @Column(name = "password")
    private String password;

    public UserLoginData(UserLoginDTO userLoginDTO) {
        this.firstName = userLoginDTO.getFirstname();
        this.lastName = userLoginDTO.getLastname();
        this.eMail = userLoginDTO.getEMail();
        this.password = userLoginDTO.getPassword();
    }
}