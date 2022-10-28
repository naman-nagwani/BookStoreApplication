package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.DTO.BookDTO;
import com.bridgelabz.bookstoreapplication.entity.BookData;

import java.util.List;

public interface IBookService {
    List<BookData> getBookData();

    BookData getBookDataByID(int bookId);

    BookData createBookData(BookDTO bookDTO);

    BookData updateBookData(int bookId, BookDTO bookDTO);

    void deleteBookData(int bookId);
}
