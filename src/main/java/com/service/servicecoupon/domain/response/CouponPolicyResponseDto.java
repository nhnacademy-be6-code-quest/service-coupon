package com.service.servicecoupon.domain.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CouponPolicyResponseDto {
    private long couponPolicyId;
    private String PolicyDescription;
    private DiscountType discountType;
    private long discountValue;
    private long minPurchaseAmount;
    private long maxDiscountAmount;

    public CouponPolicyResponseDto(CouponPolicy couponPolicy){
        this(couponPolicy.getCouponPolicyId(),
                couponPolicy.getPolicyDescription(),
                couponPolicy.getDiscountType(),
                couponPolicy.getDiscountValue(),
                couponPolicy.getMinPurchaseAmount(),
                couponPolicy.getMaxDiscountAmount()
                );

    }}