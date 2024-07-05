package com.service.servicecoupon.exception;

import jakarta.ws.rs.NotFoundException;

public class CouponNotFoundException extends NotFoundException {

    public CouponNotFoundException(String message) {
        super(message);
    }
}
