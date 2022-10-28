package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.DTO.ResponseDTO;
import com.bridgelabz.bookstoreapplication.DTO.UserLoginDTO;
import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import com.bridgelabz.bookstoreapplication.service.IUserLoginService;
import com.bridgelabz.bookstoreapplication.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/userloginservice")
public class UserLoginController {
    @Autowired
    private IUserLoginService userLoginService;

    @Autowired
    private JWTToken jwtToken;


    /*@GetMapping("/testGet/{token}")
    public ResponseEntity<ResponseDTO> getLoginData(@PathVariable("token") String token) {
        int loginData = userLoginService.getUserLoginData(token);
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", loginData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }*/


    @PostMapping("/registration")
    public ResponseEntity<ResponseDTO> addUserLoginData(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        UserLoginData userLoginData  = userLoginService.createUserLoginData(userLoginDTO);
        ResponseDTO respDTO = new ResponseDTO("Created User Login Data Successfully", userLoginData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserLoginDTO loginDTO) {
        UserLoginData userLoginData  = userLoginService.validateUserLoginData(loginDTO);

        String message;
        if (userLoginData==null)
            message = "Login error : Not a valid email";
        else if (userLoginData.getPassword().equals(loginDTO.getPassword())){
            message = "Login Successful";
        } else
            message = "Login error : enter a valid password";

        ResponseDTO respDTO = new ResponseDTO(message, jwtToken.createToken(userLoginData.getUserId()));
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/resetPassword")
    public ResponseEntity<ResponseDTO> resetPassword(@RequestHeader String token,@RequestHeader String newPassword) {
        String  message  = userLoginService.resetPassword(token,newPassword);
        ResponseDTO respDTO = new ResponseDTO(message,token);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<ResponseDTO> forgetPassword(@RequestParam String eMail) {
        String status = userLoginService.forgetPassword(eMail);
        ResponseDTO respDTO = new ResponseDTO(status,eMail);
        return new ResponseEntity<>(respDTO,HttpStatus.OK);
    }

}
