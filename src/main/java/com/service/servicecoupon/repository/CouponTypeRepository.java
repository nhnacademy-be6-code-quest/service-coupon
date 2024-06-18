package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.CouponType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponTypeRepository extends JpaRepository<CouponType,Long> {
}
