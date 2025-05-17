package com.shreya.spring.exception;

public class CartItemAddFailedException extends RuntimeException {
    public CartItemAddFailedException(String message) {
        super(message);
    }
}
