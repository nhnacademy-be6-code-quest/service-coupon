package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryCouponRepository extends JpaRepository<ProductCategoryCoupon, Long> {
    ProductCategoryCoupon findByCategoryPolicy_CouponPolicyId(Long couponPolicyId);


}
