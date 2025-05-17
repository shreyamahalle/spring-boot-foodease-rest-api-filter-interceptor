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
    public ResponseEntity<String> addCartItem(@RequestBody CartItem cartItem) {
        log.info("API called: add cartItem {}", cartItem);
        try {
            boolean added = cartItemService.addCartItem(cartItem);
            if (added) {
                return ResponseEntity.status(HttpStatus.CREATED).body("CartItem added successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add CartItem.");
            }
        } catch (CartItemAddFailedException | SQLException ex) {
            log.error("CartItem add failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        log.info("API called: get all cartItems");
        List<CartItem> cartItems = cartItemService.retrieveCartItem();
        if (cartItems == null || cartItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/cartItem/{id}")
    public ResponseEntity<?> getCartItemById(@PathVariable int id) {
        log.info("API called: get CartItem by Id {}", id);
        try {
            CartItem cartItem = cartItemService.getCartItem(id);
            return ResponseEntity.ok(cartItem);
        } catch (CartItemNotFoundException ex) {
            log.warn("CartItem not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/cartItem/{id}")
    public ResponseEntity<String> deleteCartItem(@PathVariable int id) {
        log.info("API called: delete CartItem by Id {}", id);
        try {
            boolean deleted = cartItemService.deleteCartItem(id);
            if (deleted) {
                return ResponseEntity.ok("CartItem deleted successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CartItem not found with id: " + id);
            }
        } catch (CartItemNotFoundException ex) {
            log.warn("CartItem not found: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/cartItem")
    public ResponseEntity<String> updatePartialCartItem(@RequestBody CartItem cartItem) {
        log.info("API called: update partial CartItem {}", cartItem);
        try {
            boolean updated = cartItemService.updatePartialCartItem(cartItem);
            if (updated) {
                return ResponseEntity.ok("CartItem updated partially.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update CartItem.");
            }
        } catch (CartItemUpdateFailedException ex) {
            log.error("Partial update failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/cartItem")
    public ResponseEntity<String> updateCartItem(@RequestBody CartItem cartItem) {
        log.info("API called: update CartItem {}", cartItem);
        try {
            boolean updated = cartItemService.updateCartItem(cartItem);
            if (updated) {
                return ResponseEntity.ok("CartItem updated successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update CartItem.");
            }
        } catch (CartItemUpdateFailedException | SQLException ex) {
            log.error("Update failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
