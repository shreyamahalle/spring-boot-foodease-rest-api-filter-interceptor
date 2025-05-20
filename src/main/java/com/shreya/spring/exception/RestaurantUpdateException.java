package com.shreya.spring.exception;

public class RestaurantUpdateException extends RuntimeException {
    public RestaurantUpdateException(String message) {
        super(message);
    }
}
