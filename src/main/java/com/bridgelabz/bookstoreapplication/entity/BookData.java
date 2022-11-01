package com.bridgelabz.bookstoreapplication.entity;

import com.bridgelabz.bookstoreapplication.DTO.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book_data")
public class BookData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "book_name")
    private String bookName;

    private int bookPrice;

    private int bookQuantity;

    public BookData(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.bookPrice = bookDTO.getBookPrice();
        this.bookQuantity = bookDTO.getBookQuantity();
    }

    public void updateBookData(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.bookPrice = bookDTO.getBookPrice();
        this.bookQuantity = bookDTO.getBookQuantity();
    }
}
