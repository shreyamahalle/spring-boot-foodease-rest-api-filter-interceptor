package com.shreya.spring.exception;

public class CartItemUpdateFailedException extends RuntimeException {
    public CartItemUpdateFailedException(String message) {
        super(message);
    }
}
