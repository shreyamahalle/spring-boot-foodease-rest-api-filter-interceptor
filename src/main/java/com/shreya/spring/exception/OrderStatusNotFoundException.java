package com.shreya.spring.exception;

public class OrderStatusNotFoundException extends RuntimeException {
    public OrderStatusNotFoundException(String message) {
        super(message);
    }
}
