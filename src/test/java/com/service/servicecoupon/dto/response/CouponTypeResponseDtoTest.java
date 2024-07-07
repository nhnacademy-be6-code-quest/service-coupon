package com.service.servicecoupon.dto.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.service.servicecoupon.domain.CouponKind;
import org.junit.jupiter.api.Test;

class CouponTypeResponseDtoTest {

    @Test
    void testCouponTypeResponseDto() {

        CouponTypeResponseDto dto = new CouponTypeResponseDto(
            1L,
            CouponKind.BIRTHDAY
        );

        assertEquals(1L, dto.getCouponTypeId());
        assertEquals(CouponKind.BIRTHDAY, dto.getCouponKind());
    }

}
