package com.service.servicecoupon.domain.entity;

import jakarta.persistence.*;

@Entity
public class ProductCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCouponId;
    private long productCategoryId;
    @ManyToOne
    @JoinColumn(name = "productCouponList")
    private CouponPolicy couponPolicy;
}
