package com.shreya.spring.repository;

import com.shreya.spring.model.MenuItem;
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
public class MenuItemRepository {

    private final Logger log = LoggerFactory.getLogger(MenuItemRepository.class);

    public boolean addMenuItem(MenuItem menuItem) {
        String query = "INSERT INTO menu_item (name, description, price, restaurant_id) VALUES (?, ?, ?, ?)";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, menuItem.getName());
            ps.setString(2, menuItem.getDescription());
            ps.setDouble(3, menuItem.getPrice());
            ps.setLong(4, menuItem.getRestaurantId());

            int rowsAffected = ps.executeUpdate();
            log.info("MenuItem added: {}", menuItem);
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error adding MenuItem: {}", menuItem, e);
            throw new RuntimeException(e);
        }
    }

    public List<MenuItem> retrieveMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menu_item";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MenuItem menuItem = mapRowToMenuItem(rs);
                menuItems.add(menuItem);
            }

            log.info("Retrieved {} menu items", menuItems.size());

        } catch (SQLException e) {
            log.error("Error retrieving menu items", e);
            throw new RuntimeException(e);
        }

        return menuItems;
    }

    public MenuItem findById(long id) {
        String query = "SELECT * FROM menu_item WHERE id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToMenuItem(rs);
                }
            }

        } catch (SQLException e) {
            log.error("Error finding menu item by id: {}", id, e);
        }

        return null;
    }

    public boolean deleteMenuItem(long id) {
        String query = "DELETE FROM menu_item WHERE id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            log.info("Deleted menu item with id: {}", id);
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error deleting menu item with id: {}", id, e);
        }

        return false;
    }

    public boolean updateMenuItem(MenuItem menuItem) {
        String query = "UPDATE menu_item SET name = ?, description = ?, price = ?, restaurant_id = ? WHERE id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, menuItem.getName());
            ps.setString(2, menuItem.getDescription());
            ps.setDouble(3, menuItem.getPrice());
            ps.setLong(4, menuItem.getRestaurantId());
            ps.setLong(5, menuItem.getId());

            int rowsAffected = ps.executeUpdate();
            log.info("Updated menu item with id: {}", menuItem.getId());
            return rowsAffected > 0;

        } catch (SQLException e) {
            log.error("Error updating menu item with id: {}", menuItem.getId(), e);
        }

        return false;
    }

    private MenuItem mapRowToMenuItem(ResultSet rs) throws SQLException {
        return new MenuItem(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getDouble("price"),
                rs.getLong("restaurant_id")
        );
    }
}
