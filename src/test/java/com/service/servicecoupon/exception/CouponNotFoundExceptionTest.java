package com.service.servicecoupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponNotFoundExceptionTest {

    @Test
    void testCouponNotFoundExceptionTest() {
        String message = "CouponNotFound";

        CouponNotFoundException exception = assertThrows(CouponNotFoundException.class, () -> {
            throw new CouponNotFoundException(message);
        });

        assertEquals(message, exception.getMessage());
    }

}
