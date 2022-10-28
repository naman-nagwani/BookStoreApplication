package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.DTO.BookDTO;
import com.bridgelabz.bookstoreapplication.DTO.ResponseDTO;
import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.service.IBookService;
import com.bridgelabz.bookstoreapplication.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bookservice")
public class BookController {

    @Autowired
    private IBookService bookService;

    @Autowired
    private JWTToken jwtToken;

    @RequestMapping(value = {"","/" ,"/get"})
    public ResponseEntity<ResponseDTO> getBookData() {
        List<BookData> bookDataList =bookService.getBookData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", bookDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<ResponseDTO> getBookData(@PathVariable("bookId") int bookId) {
        BookData bookData = bookService.getBookDataByID(bookId);
        ResponseDTO respDT0 = new ResponseDTO("Get Call For ID Successful", bookData);
        return new ResponseEntity<>(respDT0, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addBookData(@Valid @RequestBody BookDTO bookDTO) {
        BookData bookData  = bookService.createBookData(bookDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Book Data Successfully", jwtToken.createToken(bookData.getId()));
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable("bookId") int bookId,@Valid @RequestBody BookDTO bookDTO) {
        BookData bookData = bookService.updateBookData(bookId,bookDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Book Data Successfully", bookData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable("bookId") int bookId) {
        bookService.deleteBookData(bookId);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + bookId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

}
