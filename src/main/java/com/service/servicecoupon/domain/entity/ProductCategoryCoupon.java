package com.service.servicecoupon.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class ProductCategoryCoupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productCategoryCouponId;
    private long productCategoryId;
    @OneToOne
    @JoinColumn(name = "couponPolicyId")
    private CouponPolicy categoryPolicy;


    public ProductCategoryCoupon(long productCategoryId, CouponPolicy categoryPolicy) {
        this.productCategoryId = productCategoryId;
        this.categoryPolicy = categoryPolicy;
    }
}
