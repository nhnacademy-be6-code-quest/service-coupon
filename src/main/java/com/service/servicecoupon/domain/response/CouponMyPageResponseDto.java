package com.service.servicecoupon.domain.response;

import com.service.servicecoupon.domain.CouponKind;
import lombok.*;

@Getter
@NoArgsConstructor
@Setter
public class CouponMyPageResponseDto {
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
