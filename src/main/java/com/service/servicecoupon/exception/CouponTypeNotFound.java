package com.service.servicecoupon.exception;

import jakarta.ws.rs.NotFoundException;

public class CouponTypeNotFound extends NotFoundException {
    public CouponTypeNotFound(String message){super(message);}

}
