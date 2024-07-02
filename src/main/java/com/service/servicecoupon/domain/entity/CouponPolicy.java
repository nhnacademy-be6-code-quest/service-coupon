package com.service.servicecoupon.domain.entity;

import com.service.servicecoupon.domain.DiscountType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
