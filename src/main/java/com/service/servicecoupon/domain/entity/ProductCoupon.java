package com.service.servicecoupon.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ProductCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCouponId;
    private long productId;
    @OneToOne
    @JoinColumn(name = "couponPolicyId")
    private CouponPolicy productPolicy;

    public ProductCoupon(long productId, CouponPolicy productPolicy) {
        this.productId = productId;
        this.productPolicy = productPolicy;
    }
}
