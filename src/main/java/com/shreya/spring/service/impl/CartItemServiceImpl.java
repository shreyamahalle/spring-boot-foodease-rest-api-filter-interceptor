package com.shreya.spring.service.impl;

import com.shreya.spring.exception.CartItemAddFailedException;
import com.shreya.spring.exception.CartItemNotFoundException;
import com.shreya.spring.exception.CartItemUpdateFailedException;
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
        log.info("Saving CartItem {}", cartItem);
        boolean isAdded = cartItemRepository.addCartItem(cartItem);
        if (!isAdded) {
            log.error("Failed to add CartItem: {}", cartItem);
            throw new CartItemAddFailedException("Failed to add CartItem");
        }
        return true;
    }

    @Override
    public boolean deleteCartItem(int id) {
        log.info("Deleting cart item with ID: {}", id);
        boolean isDeleted = cartItemRepository.deleteCartItem(id);
        if (!isDeleted) {
            log.error("CartItem not found or could not be deleted for ID: {}", id);
            throw new CartItemNotFoundException("CartItem not found for ID: " + id);
        }
        return true;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) throws SQLException {
        log.info("Updating cart item: {}", cartItem);
        boolean isUpdated = cartItemRepository.updateCartItem(cartItem);
        if (!isUpdated) {
            log.error("Failed to update CartItem: {}", cartItem);
            throw new CartItemUpdateFailedException("Failed to update CartItem");
        }
        return true;
    }

    @Override
    public List<CartItem> retrieveCartItem() {
        log.info("Retrieving all cart items");
        List<CartItem> items = cartItemRepository.retrieveCartItems();
        log.info("Retrieved {} cart items", items.size());
        if (items.isEmpty()) {
            log.warn("No cart items found");
        }
        return items;
    }

    @Override
    public CartItem getCartItem(int id) {
        log.info("Fetching cart item with ID: {}", id);
        CartItem item = cartItemRepository.findById(id);
        if (item == null) {
            log.error("CartItem not found for ID: {}", id);
            throw new CartItemNotFoundException("CartItem not found for ID: " + id);
        }
        return item;
    }

    @Override
    public boolean updatePartialCartItem(CartItem cartItem) {
        log.info("Partially updating cart item: {}", cartItem);
        boolean updated = cartItemRepository.updatePartialCartItem(cartItem);
        if (!updated) {
            log.error("Failed to partially update CartItem: {}", cartItem);
            throw new CartItemUpdateFailedException("Failed to partially update CartItem");
        }
        return true;
    }
}
