package com.service.servicecoupon.dto.response;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import org.junit.jupiter.api.Test;

class PaymentCompletedCouponResponseDtoTest {

    @Test
    void testRefundCouponResponseDto() {
        PaymentCompletedCouponResponseDto dto = new PaymentCompletedCouponResponseDto();
        setField(dto,"couponId",1L);
        assertEquals(1L, dto.getCouponId());
    }
}
