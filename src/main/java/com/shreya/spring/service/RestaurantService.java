package com.shreya.spring.service;

import com.shreya.spring.model.Restaurant;

import java.sql.SQLException;
import java.util.List;

public interface RestaurantService {

    void insertRestaurant(Restaurant restaurant) throws SQLException;

    List<Restaurant> retrieveRestaurants();

    Restaurant getRestaurantById(int id);

    boolean deleteRestaurant(int id) throws SQLException;

    boolean updateRestaurant(int id) throws SQLException;
}
