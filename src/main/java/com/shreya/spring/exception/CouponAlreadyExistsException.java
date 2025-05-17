package com.shreya.spring.exception;

public class CouponAlreadyExistsException extends RuntimeException {
    public CouponAlreadyExistsException(String message) {
        super(message);
    }
}

