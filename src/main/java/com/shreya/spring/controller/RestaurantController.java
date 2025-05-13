package com.shreya.spring.controller;

import com.shreya.spring.model.Restaurant;
import com.shreya.spring.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/restaurantManagement")
public class RestaurantController {

    private final Logger log = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public String createRestaurant(@RequestBody Restaurant restaurant) {
        log.info("Creating restaurant: {}", restaurant);
        try {
            restaurantService.insertRestaurant(restaurant);
            return "Restaurant added successfully!";
        } catch (SQLException e) {
            log.error("Error inserting restaurant: {}", e.getMessage(), e);
            throw new RuntimeException("Error while inserting restaurant", e);
        }
    }

    @GetMapping("/restaurant")
    public List<Restaurant> getAllRestaurants() {
        log.info("Fetching all restaurants");
        return restaurantService.retrieveRestaurants();
    }

    @GetMapping("/restaurant/{id}/{name}")
    public Restaurant getRestaurant(@PathVariable int id) {
        log.info("Fetching restaurant with ID: {}", id);

        Restaurant restaurant = restaurantService.getRestaurantById(id);
        if (restaurant != null) {
            log.info("Restaurant found: {}", restaurant);

        } else {
            log.warn("Restaurant not found with ID: {}", id);
        }
        return restaurant;
    }

    @DeleteMapping("/restaurant/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        log.info("Deleting restaurant with ID: {}", id);

        try {
            if (restaurantService.deleteRestaurant(id)) {
                return "Restaurant deleted successfully!";
            } else {
                return "Failed to delete restaurant.";
            }
        } catch (SQLException e) {
            log.error("Error deleting restaurant ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error while deleting restaurant", e);
        }
    }

    @PutMapping("/restaurant/{id}")
    public String updateRestaurant(@PathVariable int id) {
        log.info("Updating restaurant with ID: {}", id);
        try {
            if (restaurantService.updateRestaurant(id)) {
                return "Restaurant updated successfully!";
            } else {
                return "Failed to update restaurant.";
            }
        } catch (SQLException e) {
            log.error("Error updating restaurant ID {}: {}", id, e.getMessage(), e);
            throw new RuntimeException("Error while updating restaurant", e);
        }
    }
}


