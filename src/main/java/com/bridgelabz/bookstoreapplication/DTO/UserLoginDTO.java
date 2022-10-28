package com.bridgelabz.bookstoreapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserLoginDTO {

    private String firstname;
    private String lastname;

    @Email(message = "Enter a valid mail")
    private String eMail;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*\\p{Punct}).{8,}$",message = "Enter a valid password")
    private String password;
}

