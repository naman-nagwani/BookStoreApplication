package com.bridgelabz.bookstoreapplication.DTO;

import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

    private int userId;
    private int bookId;
    private int quantity;
    private int totalPrice;

}
