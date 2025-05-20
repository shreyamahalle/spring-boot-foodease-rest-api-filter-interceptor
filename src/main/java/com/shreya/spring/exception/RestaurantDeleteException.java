package com.shreya.spring.exception;

public class RestaurantDeleteException extends RuntimeException {
    public RestaurantDeleteException(String message) {
        super(message);
    }
}
