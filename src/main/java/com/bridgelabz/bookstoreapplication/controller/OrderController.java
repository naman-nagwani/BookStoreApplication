package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.DTO.ResponseDTO;
import com.bridgelabz.bookstoreapplication.entity.OrderData;
import com.bridgelabz.bookstoreapplication.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderservice")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/placeorder")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestParam int cartId,@RequestParam String address) {
        OrderData orderData = orderService.placeOrder(cartId,address);
        ResponseDTO respDTO = new ResponseDTO("order placed successfully", orderData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
    @PutMapping("/cancelorder")/*take token as well*/
    public ResponseEntity<ResponseDTO> cancelOrder(@RequestParam int orderId) {
        OrderData orderData = orderService.cancelOrder(orderId);
        ResponseDTO respDTO = new ResponseDTO("order canceled successfully", orderData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/getallorders")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderData> orderDataList = orderService.getAllOrders();
        ResponseDTO respDTO = new ResponseDTO("Get call for all Orders :", orderDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/getallordersforuser")
    public ResponseEntity<ResponseDTO> getAllOrdersForUser(@RequestHeader String token) {
        List<OrderData> orderDataList = orderService.getAllOrdersForUser(token);
        ResponseDTO respDTO = new ResponseDTO("Get call for all Orders for user :", orderDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
