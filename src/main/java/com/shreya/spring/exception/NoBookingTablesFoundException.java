package com.shreya.spring.exception;


public class NoBookingTablesFoundException extends RuntimeException {
    public NoBookingTablesFoundException() {
        super("No booking tables found.");
    }

    public NoBookingTablesFoundException(String message) {
        super(message);
    }
}
