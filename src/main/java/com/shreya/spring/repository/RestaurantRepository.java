package com.shreya.spring.repository;

import com.shreya.spring.model.Restaurant;
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

public class RestaurantRepository {

    private final Logger log = LoggerFactory.getLogger(RestaurantRepository.class);

    public void addRestaurant(Restaurant restaurant) throws SQLException {
        String query = "INSERT INTO restaurant (id, name, city, area) VALUES (?, ?, ?, ?)";
        log.info("Adding restaurant: {}", restaurant);
        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, restaurant.getId());
            preparedStatement.setString(2, restaurant.getName());
            preparedStatement.setString(3, restaurant.getCity());
            preparedStatement.setString(4, restaurant.getArea());

            preparedStatement.executeUpdate();
            System.out.println("Inserting restaurant data to table: " + restaurant);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Restaurant> retrieveRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        String query = "SELECT * FROM restaurant";
        log.info("Retrieving all restaurants");

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String city = resultSet.getString("city");
                String area = resultSet.getString("area");

                Restaurant restaurant = new Restaurant(id, name, city, area);
                restaurants.add(restaurant);
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
        return restaurants;
    }

    public Restaurant retrieveRestaurant(int id) {
        Restaurant restaurant = null;
        String sql = "SELECT * FROM restaurant WHERE id = ? AND name = ?";
        log.info("Retrieving restaurant with ID: {}", id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String city = resultSet.getString("city");
                    String area = resultSet.getString("area");

                    restaurant = new Restaurant(id, name, city, area);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
        return restaurant;
    }

    public boolean deleteRestaurant(int id) throws SQLException {
        String query = "DELETE FROM restaurant WHERE id = ?";
        log.info("Deleting restaurant with ID: {}", id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateRestaurant(int id) throws SQLException {
        String sql = "UPDATE restaurant SET id = ? WHERE id = ?";
        log.info("Updating restaurant ID to: {}", id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
