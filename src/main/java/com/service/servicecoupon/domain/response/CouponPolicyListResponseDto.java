package com.service.servicecoupon.domain.response;


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
}

