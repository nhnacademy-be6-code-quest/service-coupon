package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy,Long> {

    CouponPolicy findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc(String couponPolicyDescription);

    CouponPolicy findTop1ByCouponPolicyDescriptionContainingAndMaxDiscountAmountLessThanEqualOrderByMaxDiscountAmountDesc(String couponPolicyDescription, long maxDiscountAmount);
}
