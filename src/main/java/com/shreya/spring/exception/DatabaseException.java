package com.shreya.spring.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message, cause);
    }
}
