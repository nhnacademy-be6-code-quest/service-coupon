package com.service.servicecoupon.domain.entity;

import com.service.servicecoupon.domain.DiscountType;
import jakarta.persistence.*;
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
    private Long productId;
    private Long productCategoryId;
    @Column(nullable = false)
    private String couponPolicyDescription;
    private DiscountType discountType;
    private long discountValue;
    @Column(nullable = false)
    private long minPurchaseAmount;
    @Column(nullable = false)
    private long maxDiscountAmount;

}
