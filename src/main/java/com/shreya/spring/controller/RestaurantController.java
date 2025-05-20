package com.shreya.spring.controller;

import com.shreya.spring.model.Restaurant;
import com.shreya.spring.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createRestaurant(@RequestBody Restaurant restaurant) throws SQLException {
        restaurantService.insertRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body("Restaurant added successfully!");
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.retrieveRestaurants());
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable int id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.ok(restaurant);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) throws SQLException {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok("Restaurant deleted successfully!");
    }

    @PutMapping("/restaurant/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable int id) throws SQLException {
        restaurantService.updateRestaurant(id);
        return ResponseEntity.ok("Restaurant updated successfully!");
    }
}
