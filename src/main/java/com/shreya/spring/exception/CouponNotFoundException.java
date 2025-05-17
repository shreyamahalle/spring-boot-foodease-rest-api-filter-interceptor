package com.shreya.spring.exception;


public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(String message) {
        super(message);
    }
}