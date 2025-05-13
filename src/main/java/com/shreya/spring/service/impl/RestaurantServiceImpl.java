package com.shreya.spring.service.impl;

import com.shreya.spring.model.Restaurant;
import com.shreya.spring.repository.RestaurantRepository;
import com.shreya.spring.service.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void insertRestaurant(Restaurant restaurant) throws SQLException {
        log.info("add restaurant {}", restaurant);
        restaurantRepository.addRestaurant(restaurant);
    }

    @Override
    public List<Restaurant> retrieveRestaurants() {
        log.info("all restaurants");
        return restaurantRepository.retrieveRestaurants();
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        log.info("get Restaurant ById {}", id);
        return restaurantRepository.retrieveRestaurant(id);
    }

    @Override
    public boolean deleteRestaurant(int id) throws SQLException {
        log.info("delete Restaurant {}", id);
        return restaurantRepository.deleteRestaurant(id);
    }

    @Override
    public boolean updateRestaurant(int id) throws SQLException {
        log.info("Update Restaurant {}", id);
        return restaurantRepository.updateRestaurant(id);
    }
}
