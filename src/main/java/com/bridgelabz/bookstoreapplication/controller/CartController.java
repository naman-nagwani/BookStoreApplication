package com.bridgelabz.bookstoreapplication.controller;

import com.bridgelabz.bookstoreapplication.DTO.CartDTO;
import com.bridgelabz.bookstoreapplication.DTO.ResponseDTO;
import com.bridgelabz.bookstoreapplication.entity.BookData;
import com.bridgelabz.bookstoreapplication.entity.CartData;
import com.bridgelabz.bookstoreapplication.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartservice")
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping("/addtocart")
    public ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        CartData cartData = cartService.addTocart(cartDTO);
        ResponseDTO respDTO = new ResponseDTO("Added to cart successfully", cartData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/removefromcart")
    public ResponseEntity<ResponseDTO> removefromCart(@RequestParam int id) {
        cartService.removeFromCart(id);
        ResponseDTO respDTO = new ResponseDTO("Remove from cart successfully", "Delete Id : "+id);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/updatequantity")
    public ResponseEntity<ResponseDTO> updateCartQuantity(@RequestParam int cartId,@RequestParam int newQuantity) {
        CartData cartData = cartService.updateQuantity(cartId,newQuantity);
        ResponseDTO respDTO = new ResponseDTO("Quantity updated successfully", cartData);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/allcartitemsforuser")
    public ResponseEntity<ResponseDTO> getAllCartItemsForUser(@RequestHeader String token) {
        List<BookData> bookDataList = cartService.getAllCartItemsForUser(token);
        ResponseDTO respDTO = new ResponseDTO("All items for user : ", bookDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/allcartitems")
    public ResponseEntity<ResponseDTO> getAllCartItems() {
        List<BookData> bookDataList = cartService.getAllCartItems();
        ResponseDTO respDTO = new ResponseDTO("All items : ", bookDataList);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }

}
