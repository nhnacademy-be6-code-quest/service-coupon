package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.clientId = :clientId AND c.status = 1")
    List<Coupon> findAvailableCouponsByClientId(@Param("clientId") long clientId);

    List<Coupon> findByExpirationDateBefore(LocalDate expirationDate);

    Page<Coupon> findByClientIdAndStatus(long clientId, Pageable pageable, Status status);

    Page<Coupon> findAllByStatus (Pageable pageable, Status status);
}
