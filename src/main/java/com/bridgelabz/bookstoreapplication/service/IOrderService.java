package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.entity.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData placeOrder(int cartId, String address);

    OrderData cancelOrder(int orderId);

    List<OrderData> getAllOrders();

    List<OrderData> getAllOrdersForUser(String token);
}
