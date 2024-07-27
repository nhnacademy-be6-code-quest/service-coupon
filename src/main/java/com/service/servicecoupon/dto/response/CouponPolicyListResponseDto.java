package com.service.servicecoupon.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Setter
public class CouponPolicyListResponseDto  {

    long couponPolicyId;
    String couponPolicyDescription;
    String discountType;
    long discountValue;

}

