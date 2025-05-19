package com.shreya.spring.controller;

import com.shreya.spring.exception.CartItemAddFailedException;
import com.shreya.spring.exception.CartItemNotFoundException;
import com.shreya.spring.exception.CartItemUpdateFailedException;
import com.shreya.spring.model.CartItem;
import com.shreya.spring.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/cartItemManagement")
public class CartItemController {

    private final Logger log = LoggerFactory.getLogger(CartItemController.class);

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/cartItem")
    public ResponseEntity<String> addCartItem(@RequestBody CartItem cartItem) throws SQLException {
        log.info("API called: add cartItem {}", cartItem);
        boolean added = cartItemService.addCartItem(cartItem);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("CartItem added successfully.");
        } else {
            throw new CartItemAddFailedException("Failed to add CartItem.");
        }
    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        log.info("API called: get all cartItems");
        List<CartItem> cartItems = cartItemService.retrieveCartItem();
        if (cartItems == null || cartItems.isEmpty()) {
            throw new CartItemNotFoundException("No cart items found.");
        }
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/cartItem/{id}")
    public ResponseEntity<CartItem> getCartItemById(@PathVariable int id) {
        log.info("API called: get CartItem by Id {}", id);
        CartItem cartItem = cartItemService.getCartItem(id);
        return ResponseEntity.ok(cartItem);
    }

    @DeleteMapping("/cartItem/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
        log.info("API called: delete CartItem by Id {}", id);
        boolean deleted = cartItemService.deleteCartItem(id);
        if (deleted) {
            return ResponseEntity.ok("CartItem deleted successfully.");
        } else {
            throw new CartItemNotFoundException("CartItem not found with id: " + id);
        }
    }

    @PatchMapping("/cartItem")
    public ResponseEntity<String> updatePartialCartItem(@RequestBody CartItem cartItem) {
        log.info("API called: update partial CartItem {}", cartItem);
        boolean updated = cartItemService.updatePartialCartItem(cartItem);
        if (updated) {
            return ResponseEntity.ok("CartItem updated partially.");
        } else {
            throw new CartItemUpdateFailedException("Failed to update CartItem partially.");
        }
    }

    @PutMapping("/cartItem")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItem cartItem) throws SQLException {
        log.info("API called: update CartItem {}", cartItem);
        boolean updated = cartItemService.updateCartItem(cartItem);
        if (updated) {
            return ResponseEntity.ok("CartItem updated successfully.");
        } else {
            throw new CartItemUpdateFailedException("Failed to update CartItem.");
        }
    }
}
