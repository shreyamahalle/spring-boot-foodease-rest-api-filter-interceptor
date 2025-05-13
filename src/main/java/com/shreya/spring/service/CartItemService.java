package com.shreya.spring.service;

import com.shreya.spring.model.CartItem;

import java.sql.SQLException;
import java.util.List;

public interface CartItemService {

    boolean addCartItem(CartItem cartItem) throws SQLException;

    boolean deleteCartItem(int id);

    boolean updateCartItem(CartItem cartItem) throws SQLException;

    List<CartItem> retrieveCartItem();

    CartItem getCartItem(int id);

    boolean updatePartialCartItem(CartItem cartItem);
}