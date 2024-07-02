package com.service.servicecoupon.domain.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.servicecoupon.domain.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CouponPolicyListResponseDto {

    private long couponPolicyId;
    private String couponPolicyDescription;
    private DiscountType discountType;
    private long discountValue;
    private long minPurchaseAmount;
    private long maxDiscountAmount;


    @JsonCreator
    public CouponPolicyListResponseDto(
            @JsonProperty("couponPolicyId") Long couponPolicyId,
            @JsonProperty("couponPolicyDescription") String couponPolicyDescription,
            @JsonProperty("discountType") DiscountType discountType,
            @JsonProperty("discountValue") int discountValue,
            @JsonProperty("minPurchaseAmount") int minPurchaseAmount,
            @JsonProperty("maxDiscountAmount") int maxDiscountAmount) {
        this.couponPolicyId = couponPolicyId;
        this.couponPolicyDescription = couponPolicyDescription;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minPurchaseAmount = minPurchaseAmount;
        this.maxDiscountAmount = maxDiscountAmount;
    }
}

