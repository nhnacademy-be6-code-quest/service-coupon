package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    @Query("SELECT c FROM Coupon c WHERE c.clientId = :clientId AND c.status = 1")
    List<Coupon> findAvailableCouponsByClientId(@Param("clientId") long clientId);
    List<Coupon> findByExpirationDateBefore(LocalDate expirationDate);
}
