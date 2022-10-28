package com.bridgelabz.bookstoreapplication.service;

import com.bridgelabz.bookstoreapplication.DTO.BookDTO;
import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.exception.BookException;
import com.bridgelabz.bookstoreapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public List<BookData> getBookData() {
        return bookRepository.findAll();
    }

    @Override
    public BookData getBookDataByID(int bookId) {
        return bookRepository.findById(bookId).orElseThrow(()->new BookException("Id not found in the repository"));
    }

    @Override
    public BookData createBookData(BookDTO bookDTO) {
        BookData bookData = new BookData(bookDTO);
        return bookRepository.save(bookData);
    }

    @Override
    public BookData updateBookData(int bookId, BookDTO bookDTO) {
        BookData bookData = this.getBookDataByID(bookId);
        bookData.updateBookData(bookDTO);
        return bookRepository.save(bookData);
    }

    @Override
    public void deleteBookData(int bookId) {
        BookData bookData = this.getBookDataByID(bookId);
        bookRepository.delete(bookData);
    }

    @Override
    public void updateBookQuantity(int bookId,int bookQuantity) {
        BookData bookData = this.getBookDataByID(bookId);
        bookData.setBookQuantity(bookQuantity);
        bookRepository.save(bookData);
    }
}
