package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.entity.EmailDetails;

public interface IEmailService {
    String sendSimpleMail(EmailDetails details);
}
