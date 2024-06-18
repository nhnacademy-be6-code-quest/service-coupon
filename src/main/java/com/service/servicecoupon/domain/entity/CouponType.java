package com.service.servicecoupon.domain.entity;

import com.service.servicecoupon.domain.CouponKind;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CouponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponTypeId;
    private CouponKind couponKind;
    @OneToMany
    private List<Coupon> coupons =new ArrayList<>();
}
