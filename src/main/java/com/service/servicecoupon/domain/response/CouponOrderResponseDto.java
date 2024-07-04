package com.service.servicecoupon.domain.response;


import com.service.servicecoupon.domain.DiscountType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class CouponOrderResponseDto {
    long couponId;
    CouponPolicy couponPolicy;
    ProductCoupon productCoupon;
    CategoryCoupon categoryCoupon;

    @Setter
    @NoArgsConstructor
    @Getter
    public static class CouponPolicy {
        String couponPolicyDescription;
        String discountType;
        long discountValue;
        long minPurchaseAmount;
        long maxDiscountAmount;    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class ProductCoupon {
        Long productId;
    }
    @Setter
    @Getter
    @NoArgsConstructor
    public static class CategoryCoupon {
        Long productCategoryId;
    }

}