package com.shreya.spring.exception;

public class CouponAlreadyExistsException extends RuntimeException {
    public CouponAlreadyExistsException() {
        super("Coupon already exists.");
    }

    public CouponAlreadyExistsException(String message) {
        super(message);
    }
}
