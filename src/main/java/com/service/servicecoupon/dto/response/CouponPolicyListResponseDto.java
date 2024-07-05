package com.service.servicecoupon.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CouponPolicyListResponseDto {

    long couponPolicyId;
    String couponPolicyDescription;
    String discountType;
    long discountValue;
    long minPurchaseAmount;
    long maxDiscountAmount;

    public CouponPolicyListResponseDto(long couponPolicyId, String couponPolicyDescription,
        String discountType, long discountValue, long minPurchaseAmount, long maxDiscountAmount) {
        this.couponPolicyId = couponPolicyId;
        this.couponPolicyDescription = couponPolicyDescription;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minPurchaseAmount = minPurchaseAmount;
        this.maxDiscountAmount = maxDiscountAmount;
    }
}

