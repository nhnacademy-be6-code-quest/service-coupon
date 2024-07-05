package com.service.servicecoupon.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class CouponMyPageCouponIssuedResponseDto {
    String issuedDate;
    String expirationDate;
    String status;
    CouponType couponType;
    CouponPolicy couponPolicy;
    @Getter
    @NoArgsConstructor
    @Setter
    public static class CouponType{
        String couponKind;
    }
    @NoArgsConstructor
    @Getter
    @Setter
    public static class CouponPolicy{
        private String couponPolicyDescription;
        private String discountType;
        private long discountValue;
        private long minPurchaseAmount;
        private long maxDiscountAmount;
    }

}
