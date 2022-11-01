package com.bridgelabz.bookstoreapplication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_data")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    private LocalDate orderDate;

    private int price;

    private int quantity;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserLoginData userLoginData;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookData bookData;

    private boolean cancel;

    public OrderData(LocalDate orderDate, int price, int quantity, String address, UserLoginData userLoginData, BookData bookData, boolean cancel) {
        this.orderDate = orderDate;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.userLoginData = userLoginData;
        this.bookData = bookData;
        this.cancel = cancel;
    }
}
