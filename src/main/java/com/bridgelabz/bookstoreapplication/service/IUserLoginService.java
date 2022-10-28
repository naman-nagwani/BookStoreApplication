package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.DTO.UserLoginDTO;
import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import com.bridgelabz.bookstoreapplication.util.JWTToken;

public interface IUserLoginService {

    int getUserLoginData(String token);

    UserLoginData createUserLoginData(UserLoginDTO userLoginDTO);

    UserLoginData validateUserLoginData(UserLoginDTO loginDTO);

    String resetPassword(String token,String newPassword);

    String forgetPassword(String eMail);
}
