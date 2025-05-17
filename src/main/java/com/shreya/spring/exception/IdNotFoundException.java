package com.shreya.spring.exception;

public class IdNotFoundException extends RuntimeException {

    public IdNotFoundException() {
        super("Requested ID not found.");
    }

    public IdNotFoundException(String message) {
        super(message);
    }
}
