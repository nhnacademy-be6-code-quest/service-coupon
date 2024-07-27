package com.service.servicecoupon.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.service.servicecoupon.dto.message.RefundCouponMessageDto;
import org.junit.jupiter.api.Test;

class RefundCouponResponseDtoTest {

    @Test
    void testRefundCouponResponseDto() {
        RefundCouponMessageDto dto = new RefundCouponMessageDto();
        setField(dto,"couponId",1L);
        assertEquals(1L, dto.getCouponId());
    }
}
