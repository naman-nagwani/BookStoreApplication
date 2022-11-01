package com.bridgelabz.bookstoreapplication.DTO;

import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private LocalDate orderDate;
    private int price;
    private int quantity;
    private String address;
    private int userId;
    private int bookId;
    private boolean cancel;
}
