package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    List<CouponResponseDto> findByClientId(long clientId);

}
