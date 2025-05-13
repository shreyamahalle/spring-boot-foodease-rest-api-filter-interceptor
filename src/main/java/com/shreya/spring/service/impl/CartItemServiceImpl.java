package com.shreya.spring.service.impl;

import com.shreya.spring.model.CartItem;
import com.shreya.spring.repository.CartItemRepository;
import com.shreya.spring.service.CartItemService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final Logger log = LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    private final CartItemRepository cartItemRepository;

    @Override
    public boolean addCartItem(CartItem cartItem) throws SQLException {
        log.info("Saving CartItem{}", cartItem);
        return cartItemRepository.addCartItem(cartItem);
    }

    @Override
    public boolean deleteCartItem(int id) {
        log.info("Deleting cart item with ID: {}", id);
        return cartItemRepository.deleteCartItem(id);

    }

    @Override
    public boolean updateCartItem(CartItem cartItem) throws SQLException {
        log.info("Updating cart item: {}", cartItem);
        return cartItemRepository.updateCartItem(cartItem);
    }

    @Override
    public List<CartItem> retrieveCartItem() {
        log.info("Retrieving all cart items");
        List<CartItem> items = cartItemRepository.retrieveCartItems();
        log.info("Retrieved {} cart items", items.size());
        return items;
    }

    @Override
    public CartItem getCartItem(int id) {
        log.info("Fetching cart item with ID: {}", id);
        CartItem item = cartItemRepository.findById(id);
        if (item != null) {
            log.info("Cart item found: {}", item);
        } else {
            log.warn("Cart item not found for ID: {}", id);
        }
        return item;
    }

    @Override
    public boolean updatePartialCartItem(CartItem cartItem) {
        log.info("Partially updating cart item: {}", cartItem);
        boolean updated = cartItemRepository.updatePartialCartItem(cartItem);
        log.info("Partial update success: {}", updated);
        return updated;
    }
}