package com.bridgelabz.bookstoreapplication;

import com.bridgelabz.bookstoreapplication.service.EmailSenderService;
import com.bridgelabz.bookstoreapplication.util.JWTToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public JWTToken jwtToken() {
        return new JWTToken();
    }

    @Bean
    public EmailSenderService emailSenderService()
    {
        return new EmailSenderService();
    }
}
