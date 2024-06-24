package com.service.servicecoupon.domain.request;

import com.service.servicecoupon.domain.DiscountType;

public record CouponPolicyRequestDto(
        Long productId,
        Long productCategoryId,
        String PolicyDescription,
        DiscountType discountType,
        long discountValue,
        long minPurchaseAmount,
        long maxDiscountAmount) {
}
