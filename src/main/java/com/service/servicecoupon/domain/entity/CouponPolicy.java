package com.service.servicecoupon.domain.entity;

import com.service.servicecoupon.domain.DiscountType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CouponPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponPolicyId;
    private long productId;
    private String couponPolicyDescription;
    private DiscountType discountType;
    private long discountValue;
    private long minPurchaseAmount;
    private long maxDiscountAmount;

}
