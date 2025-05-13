package com.shreya.spring.controller;

import com.shreya.spring.model.CartItem;
import com.shreya.spring.service.CartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public boolean addCardItem(@RequestBody CartItem cartItem) throws SQLException {
        log.info("API called: add cardItem {}", cartItem);
        return cartItemService.addCartItem(cartItem);
    }

    @GetMapping("/cartItem")
    public List<CartItem> getAllCartItems() {
        log.info("API called: all cardItem ");
        return cartItemService.retrieveCartItem();
    }

    @GetMapping("/cartItem/{id}")
    public CartItem getCartItemById(@PathVariable int id) {
        log.info("API called:get CartItem By Id {}", id);
        return cartItemService.getCartItem(id);
    }

    @DeleteMapping("/cartItem/{id}")
    public boolean deleteCartItem(@PathVariable int id) {
        log.info("API called:delete CartItem By Id {}", id);
        return cartItemService.deleteCartItem(id);
    }

    // Partial Update Cart Item
    @PatchMapping("/cartItem")
    public boolean updatePartialCartItem(@RequestBody CartItem cartItem) {
        log.info("API called:Update Partial CartItem {}", cartItem);
        return cartItemService.updatePartialCartItem(cartItem);
    }

    @PutMapping("/cartItem")
    public boolean updateCartItem(CartItem cartItem) throws SQLException {
        log.info("API called:Update CartItem {}", cartItem);
        return cartItemService.updateCartItem(cartItem);
    }
}
