package com.shreya.spring.exception;

public class OrderStatusAlreadyExistsException extends RuntimeException {
    public OrderStatusAlreadyExistsException(String message) {
        super(message);
    }
}
