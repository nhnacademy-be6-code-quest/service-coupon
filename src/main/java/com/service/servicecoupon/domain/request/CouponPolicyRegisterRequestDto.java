package com.service.servicecoupon.domain.request;

import com.service.servicecoupon.domain.DiscountType;

public record CouponPolicyRegisterRequestDto(
        String couponPolicyDescription,
        DiscountType discountType,
        long discountValue,
        long minPurchaseAmount,
        long maxDiscountAmount,
        long id,
        String typeName
        ) {
}
