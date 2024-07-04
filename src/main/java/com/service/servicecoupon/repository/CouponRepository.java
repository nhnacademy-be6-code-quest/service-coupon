package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    Page<Coupon> findByClientId(long clientId, Pageable pageable);
    List<Coupon> findByExpirationDateBefore(LocalDate expirationDate);
}
