package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.DTO.CartDTO;
import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.entity.CartData;

import java.util.List;

public interface ICartService {
    CartData addTocart(CartDTO cartDTO);

    void removeFromCart(int id);

    CartData updateQuantity(int cartId, int newQuantity);

    List<BookData> getAllCartItemsForUser(String token);

    List<BookData> getAllCartItems();
}
