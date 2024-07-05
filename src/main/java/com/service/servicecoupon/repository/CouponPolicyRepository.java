package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy,Long> {
    @Query("SELECT c FROM CouponPolicy c WHERE c.couponPolicyDescription LIKE %:couponPolicyDescription% ORDER BY c.id DESC")
    CouponPolicy findTopByCouponPolicyDescriptionContainingOrderByIdDesc(@Param("couponPolicyDescription") String couponPolicyDescription);
}
