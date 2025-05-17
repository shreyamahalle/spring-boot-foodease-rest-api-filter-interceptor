package com.shreya.spring.exception;

public class BookingAddFailedException extends RuntimeException {
    public BookingAddFailedException(String message) {
        super(message);
    }
}
