package com.service.servicecoupon.domain.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.repository.ProductCouponRepository;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CouponPolicyResponseDto {

    private long couponPolicyId;
    private String couponPolicyDescription;
    private DiscountType discountType;
    private long discountValue;
    private long minPurchaseAmount;
    private long maxDiscountAmount;
    @Setter
    private  ProductCategoryCouponResponseDto productCategoryCouponResponseDto;
    @Setter
    private  ProductCouponResponseDto productCouponResponseDto;

    public CouponPolicyResponseDto(CouponPolicy couponPolicy) {
        this.couponPolicyId = couponPolicy.getCouponPolicyId();
        this.couponPolicyDescription = couponPolicy.getCouponPolicyDescription();
        this.discountType = couponPolicy.getDiscountType();
        this.discountValue = couponPolicy.getDiscountValue();
        this.minPurchaseAmount = couponPolicy.getMinPurchaseAmount();
        this.maxDiscountAmount = couponPolicy.getMaxDiscountAmount();

    }

    }