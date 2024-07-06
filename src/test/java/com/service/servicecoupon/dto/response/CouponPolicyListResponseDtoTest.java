package com.service.servicecoupon.dto.response;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class CouponPolicyListResponseDtoTest {

    @Test
    void testCouponPolicyListResponseDto() {

        CouponPolicyListResponseDto dto = new CouponPolicyListResponseDto(
            1L,
            "Birthday Discount",
            "AMOUNTDISCOUNT",
            1000L,
            5000L,
            2000L
        );

        assertEquals(1L, dto.getCouponPolicyId());
        assertEquals("Birthday Discount", dto.getCouponPolicyDescription());
        assertEquals("AMOUNTDISCOUNT", dto.getDiscountType());
        assertEquals(1000L, dto.getDiscountValue());
        assertEquals(5000L, dto.getMinPurchaseAmount());
        assertEquals(2000L, dto.getMaxDiscountAmount());
    }

}
