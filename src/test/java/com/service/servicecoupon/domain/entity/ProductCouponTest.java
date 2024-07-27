package com.service.servicecoupon.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.service.servicecoupon.domain.DiscountType;
import org.junit.jupiter.api.Test;

class ProductCouponTest {

    @Test
    void testProductCouponEntityConstructor() {

        CouponPolicy couponPolicy = new CouponPolicy("상품 쿠폰", DiscountType.AMOUNTDISCOUNT, 10000,
            50000, 10000);

        ProductCoupon productCoupon = new ProductCoupon(1L, couponPolicy);

        assertEquals(1L, productCoupon.getProductId());
        assertEquals(couponPolicy, productCoupon.getProductPolicy());
    }
}
