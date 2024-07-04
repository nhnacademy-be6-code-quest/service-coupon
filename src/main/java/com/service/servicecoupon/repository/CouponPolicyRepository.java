package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponPolicyRepository extends JpaRepository<CouponPolicy,Long> {

}
