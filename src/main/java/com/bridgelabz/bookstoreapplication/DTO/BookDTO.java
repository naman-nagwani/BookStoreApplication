package com.bridgelabz.bookstoreapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String bookName;
    private int bookPrice;
    private int bookQuantity;

}
