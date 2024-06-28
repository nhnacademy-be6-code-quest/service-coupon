package com.service.servicecoupon.domain.entity;

import jakarta.persistence.*;

@Entity
public class ProductCategoryCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCategoryCouponId;
    private long productId;
    @ManyToOne
    @JoinColumn(name = "coupon_policy_id")
    private CouponPolicy couponPolicy;
}
