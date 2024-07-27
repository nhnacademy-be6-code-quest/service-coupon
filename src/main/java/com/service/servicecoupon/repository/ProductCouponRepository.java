package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.ProductCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCouponRepository extends JpaRepository<ProductCoupon, Long> {
    ProductCoupon findByProductPolicy_CouponPolicyId(Long couponPolicyId);
}
