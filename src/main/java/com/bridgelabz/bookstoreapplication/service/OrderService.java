package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.entity.CartData;
import com.bridgelabz.bookstoreapplication.entity.OrderData;
import com.bridgelabz.bookstoreapplication.exception.OrderException;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import com.bridgelabz.bookstoreapplication.repository.CartRepository;
import com.bridgelabz.bookstoreapplication.repository.OrderRepository;
import com.bridgelabz.bookstoreapplication.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private JWTToken jwtToken;

    @Override
    public OrderData placeOrder(int cartId, String address) {
        CartData cartData = cartRepository.findById(cartId).orElseThrow(()-> new OrderException("Cart not found"));
        OrderData orderData = new OrderData(LocalDate.now(),cartData.getTotalPrice(),cartData.getQuantity(),address,cartData.getUserLoginData(),cartData.getBookData(),false);
        orderRepository.save(orderData);
        BookData bookData = bookRepository.findById(cartData.getBookData().getId()).orElseThrow(()-> new OrderException("Book not found"));
        bookData.setBookQuantity(bookData.getBookQuantity()-orderData.getQuantity());
        bookRepository.save(bookData);
        cartRepository.delete(cartData);
        return orderData;
    }

    @Override
    public OrderData cancelOrder(int orderId) {
        OrderData orderData = orderRepository.findById(orderId).orElseThrow(()->new OrderException("Order not found"));
        orderData.setCancel(true);
        BookData bookData = bookRepository.findById(orderData.getBookData().getId()).orElseThrow(()->new OrderException("Book not found"));
        bookData.setBookQuantity(bookData.getBookQuantity()+orderData.getQuantity());
        bookRepository.save(bookData);
        orderRepository.save(orderData);
        return orderData;
    }

    @Override
    public List<OrderData> getAllOrders() {
        List<OrderData> orderDataList = orderRepository.getAllOrder();
        return orderDataList;
    }

    @Override
    public List<OrderData> getAllOrdersForUser(String token) {
        int userId = jwtToken.decodeToken(token);
        List<OrderData> orderDataList = orderRepository.getAllOrderForUser(userId);
        return orderDataList;
    }
}
