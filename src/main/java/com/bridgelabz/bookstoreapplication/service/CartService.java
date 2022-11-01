package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.DTO.CartDTO;
import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.entity.CartData;
import com.bridgelabz.bookstoreapplication.entity.UserLoginData;
import com.bridgelabz.bookstoreapplication.exception.CartException;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.repository.CartRepository;
import com.bridgelabz.bookstoreapplication.repository.UserLoginRepository;
import com.bridgelabz.bookstoreapplication.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JWTToken jwtToken;

    @Override
    public CartData addTocart(CartDTO cartDTO) {
        UserLoginData userLoginData = userLoginRepository.findById(cartDTO.getUserId()).orElseThrow(()->new CartException("User Not Found"));
        BookData bookData = bookRepository.findById(cartDTO.getBookId()).orElseThrow(()->new CartException("Book Not Found"));
        cartDTO.setTotalPrice(bookData.getBookPrice()*cartDTO.getQuantity());
        CartData cartData = new CartData(userLoginData,bookData,cartDTO.getQuantity(),cartDTO.getTotalPrice());
        cartRepository.save(cartData);
        return cartData;
    }

    @Override
    public void removeFromCart(int id) {
        CartData cartData = cartRepository.findById(id).orElseThrow(()-> new CartException("Cart with Id "+id+" not found"));
        cartRepository.delete(cartData);
    }

    @Override
    public CartData updateQuantity(String token, int cartId, int newQuantity) {
        UserLoginData userLoginData = userLoginRepository.findById(jwtToken.decodeToken(token)).orElseThrow(()->new CartException("User not found"));
        if (userLoginData!=null) {
            CartData cartData = cartRepository.findById(cartId).orElseThrow(() -> new CartException("Cart with Id " + cartId + " not found"));
            cartData.setQuantity(newQuantity);
            BookData bookData = bookRepository.findById(cartData.getBookData().getId()).orElseThrow(() -> new CartException("Book not found"));
            cartData.setTotalPrice(cartData.getQuantity() * bookData.getBookPrice());
            cartRepository.save(cartData);
            return cartData;
        } else
            return null;
    }

    @Override
    public List<BookData> getAllCartItemsForUser(String token) {
        List<CartData> cartDataList = cartRepository.getCartDataByUserId(jwtToken.decodeToken(token));
        return cartDataList.stream().map(cartData -> {
            int bookId = cartData.getBookData().getId();
            BookData bookData = bookRepository.findById(bookId).orElseThrow(()-> new CartException("Book Not found"));
            return bookData;
        }).collect(Collectors.toList());
    }

    @Override
    public List<BookData> getAllCartItems() {
        List<CartData> cartDataList = cartRepository.findAll();
        return cartDataList.stream().map(cartData -> {
            int bookId = cartData.getBookData().getId();
            BookData bookData = bookRepository.findById(bookId).orElseThrow(()-> new CartException("Book Not found"));
            return bookData;
        }).collect(Collectors.toList());
    }
}
