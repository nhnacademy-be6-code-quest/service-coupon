package com.service.servicecoupon.domain.entity;

import com.service.servicecoupon.domain.DiscountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CouponPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponPolicyId;
    @Column(nullable = false)
    private String couponPolicyDescription;
    private DiscountType discountType;
    private long discountValue;
    @Column(nullable = false)
    private long minPurchaseAmount;
    @Column(nullable = false)
    private long maxDiscountAmount;

    @Builder
    public CouponPolicy(String couponPolicyDescription, DiscountType discountType, long discountValue, long minPurchaseAmount, long maxDiscountAmount) {
        this.couponPolicyDescription = couponPolicyDescription;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.minPurchaseAmount = minPurchaseAmount;
        this.maxDiscountAmount = maxDiscountAmount;
    }
}
