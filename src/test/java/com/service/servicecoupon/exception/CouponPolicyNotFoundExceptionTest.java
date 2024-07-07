package com.service.servicecoupon.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponPolicyNotFoundExceptionTest {

    @Test
    void testCouponNotFoundExceptionTest() {
        String message = "CouponNotFound";

        CouponPolicyNotFoundException exception = assertThrows(CouponPolicyNotFoundException.class, () -> {
            throw new CouponPolicyNotFoundException(message);
        });

        assertEquals(message, exception.getMessage());
    }

}
