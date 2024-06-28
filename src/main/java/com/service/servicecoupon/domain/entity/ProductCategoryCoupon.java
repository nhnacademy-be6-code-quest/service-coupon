package com.service.servicecoupon.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProductCategoryCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCategoryCouponId;
    private long productId;
    @ManyToOne
    @JoinColumn(name = "productCategoryCoupons")
    private CouponPolicy couponPolicy;

}
