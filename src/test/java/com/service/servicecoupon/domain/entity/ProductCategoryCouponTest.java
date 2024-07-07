package com.service.servicecoupon.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.service.servicecoupon.domain.DiscountType;
import org.junit.jupiter.api.Test;

class ProductCategoryCouponTest {

    @Test
    void testProductCategoryCouponEntityConstructor() {
        CouponPolicy couponPolicy = new CouponPolicy("카테고리 쿠폰", DiscountType.AMOUNTDISCOUNT, 10000,
            50000, 10000);

        ProductCategoryCoupon productCategoryCoupon = new ProductCategoryCoupon(1L, couponPolicy);

        assertEquals(1L, productCategoryCoupon.getProductCategoryId());
        assertEquals(couponPolicy, productCategoryCoupon.getCategoryPolicy());
    }
}
