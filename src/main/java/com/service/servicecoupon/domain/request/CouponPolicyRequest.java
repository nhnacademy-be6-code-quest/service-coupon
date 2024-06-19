package com.service.servicecoupon.domain.request;

import com.service.servicecoupon.domain.DiscountType;

public record CouponPolicyRequest (Long productId, Long productCategoryId, String couponPolicyDescription, DiscountType discountType, Long discountValue, long minPurchaseAmount, long maxDiscountAmount) {
}
