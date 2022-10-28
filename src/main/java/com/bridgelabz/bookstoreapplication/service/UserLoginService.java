package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.DTO.UserLoginDTO;
import com.bridgelabz.bookstoreapplication.entity.EmailDetails;
import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import com.bridgelabz.bookstoreapplication.exception.UserLoginException;
import com.bridgelabz.bookstoreapplication.repository.UserLoginRepository;
import com.bridgelabz.bookstoreapplication.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginService implements IUserLoginService{

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    private EmailSenderService emailService;

    @Override
    public int getUserLoginData(String token) {
        return this.jwtToken.decodeToken(token);
    }

    @Override
    public UserLoginData createUserLoginData(UserLoginDTO userLoginDTO) {
        UserLoginData userLoginData = new UserLoginData(userLoginDTO);
        userLoginRepository.save(userLoginData);
        return userLoginData;
    }

    @Override
    public UserLoginData validateUserLoginData(UserLoginDTO loginDTO) {
        UserLoginData userLoginData = userLoginRepository.findUserLoginDataByEMail(loginDTO.getEMail());
        return userLoginData;
    }

    @Override
    public String resetPassword(String token,String newPassword) {
        int id = this.getUserLoginData(token);
        UserLoginData userLoginData = userLoginRepository.findById(id).orElseThrow(()->new UserLoginException("Id not Found."));
        String message;
        if (userLoginData==null)
            message = "Email not found";
        else {
            userLoginData.setPassword(newPassword);
            userLoginRepository.save(userLoginData);
            message = "Password reset successful.";
        }
        return message;
    }

    @Override
    public String forgetPassword(String eMail) {
        UserLoginData userLoginData = userLoginRepository.findUserLoginDataByEMail(eMail);
        if (userLoginData == null)
            return "Email not found";
        else {
            EmailDetails emailDetails = new EmailDetails(eMail, "A forget password request has been raised.", "Book Store Application Forgot Password", "");
            return emailService.sendSimpleMail(emailDetails);
        }
    }
}
