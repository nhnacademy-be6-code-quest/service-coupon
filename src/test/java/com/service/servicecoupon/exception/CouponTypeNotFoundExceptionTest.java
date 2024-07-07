package com.service.servicecoupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTypeNotFoundExceptionTest {

    @Test
    void testCouponTypeNotFoundExceptionTest() {
        String message = "CouponTypeNotFound";

        CouponTypeNotFoundException exception = assertThrows(CouponTypeNotFoundException.class, () -> {
            throw new CouponTypeNotFoundException(message);
        });

        assertEquals(message, exception.getMessage());
    }

}
