package com.service.servicecoupon.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.service.servicecoupon.domain.DiscountType;
import org.junit.jupiter.api.Test;

class CouponPolicyTest {

    @Test
    void testCouponPolicyEntityConstructor() {

        CouponPolicy couponPolicy = CouponPolicy.builder()
            .couponPolicyDescription("생일 쿠폰")
            .discountType(DiscountType.AMOUNTDISCOUNT)
            .discountValue(10000)
            .minPurchaseAmount(50000)
            .maxDiscountAmount(10000)
            .build();

        assertEquals("생일 쿠폰", couponPolicy.getCouponPolicyDescription());
        assertEquals(DiscountType.AMOUNTDISCOUNT, couponPolicy.getDiscountType());
        assertEquals(10000, couponPolicy.getDiscountValue());
        assertEquals(50000, couponPolicy.getMinPurchaseAmount());
        assertEquals(10000, couponPolicy.getMaxDiscountAmount());
    }
}
