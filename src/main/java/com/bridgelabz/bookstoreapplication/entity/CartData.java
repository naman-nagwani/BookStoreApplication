package com.bridgelabz.bookstoreapplication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cart_data")
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private int cartId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserLoginData userLoginData;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookData bookData;

    private int quantity;
    private int totalPrice;

    public CartData(UserLoginData userLoginData,BookData bookData,int quantity,int totalPrice) {
        this.userLoginData = userLoginData;
        this.bookData = bookData;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
