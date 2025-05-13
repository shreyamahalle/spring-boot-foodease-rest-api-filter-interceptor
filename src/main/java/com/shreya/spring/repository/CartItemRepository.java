package com.shreya.spring.repository;

import com.shreya.spring.model.CartItem;
import com.shreya.spring.service.ConnectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartItemRepository {

    private final Logger log = LoggerFactory.getLogger(CartItemRepository.class);

    public boolean addCartItem(CartItem cartItem) {
        String query = "INSERT INTO cart_item (customer_id, menu_item_id, quantity) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, cartItem.getCustomer_id());
            ps.setLong(2, cartItem.getMenu_item_id());
            ps.setInt(3, cartItem.getQuantity());

            int rowsAffected = ps.executeUpdate();
            log.info("Cart item added: {}", cartItem);
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error adding cart item: {}", cartItem, e);
            throw new RuntimeException("Error adding cart item", e);
        }
    }

    public List<CartItem> retrieveCartItems() {
        List<CartItem> cartItemList = new ArrayList<>();
        String query = "SELECT * FROM cart_item";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CartItem cartItem = new CartItem(
                        rs.getLong("id"),
                        rs.getLong("customer_id"),
                        rs.getLong("menu_item_id"),
                        rs.getInt("quantity")
                );
                cartItemList.add(cartItem);
            }
            log.info("Retrieved {} cart items", cartItemList.size());
        } catch (SQLException e) {
            log.error("Error retrieving cart items", e);
            throw new RuntimeException("Error retrieving cart items", e);
        }
        return cartItemList;
    }

    public CartItem findById(int id) {
        String query = "SELECT * FROM cart_item WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new CartItem(
                            rs.getLong("id"),
                            rs.getLong("customer_id"),
                            rs.getLong("menu_item_id"),
                            rs.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            log.error("Error finding cart item with id: {}", id, e);
        }
        return null;
    }

    public boolean deleteCartItem(int id) {
        String query = "DELETE FROM cart_item WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Cart item deleted with id: {}", id);
            } else {
                log.warn("No cart item found with id: {}", id);
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error deleting cart item with id: {}", id, e);
            throw new RuntimeException("Error deleting cart item", e);
        }
    }

    public boolean updateCartItem(CartItem cartItem) {
        String query = "UPDATE cart_item SET customer_id = ?, menu_item_id = ?, quantity = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, cartItem.getCustomer_id());
            ps.setLong(2, cartItem.getMenu_item_id());
            ps.setInt(3, cartItem.getQuantity());
            ps.setLong(4, cartItem.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Updated cart item with id: {}", cartItem.getId());
            } else {
                log.warn("No cart item found with id: {}", cartItem.getId());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error updating cart item with id: {}", cartItem.getId(), e);
            throw new RuntimeException("Error updating cart item", e);
        }
    }

    public boolean updatePartialCartItem(CartItem cartItem) {
        String query = "UPDATE cart_item SET customer_id = ?, menu_item_id = ?, quantity = ? WHERE id = ?";
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, cartItem.getCustomer_id());
            ps.setLong(2, cartItem.getMenu_item_id());
            ps.setInt(3, cartItem.getQuantity());
            ps.setLong(4, cartItem.getId());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                log.info("Partially updated cart item with id: {}", cartItem.getId());
            } else {
                log.warn("No cart item found with id: {}", cartItem.getId());
            }
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error partially updating cart item with id: {}", cartItem.getId(), e);
            throw new RuntimeException("Error partially updating cart item", e);
        }
    }
}
