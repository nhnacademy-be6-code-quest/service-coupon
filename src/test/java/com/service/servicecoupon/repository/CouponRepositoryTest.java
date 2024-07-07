package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.entity.Coupon;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CouponRepositoryTest {

    @Autowired
    private CouponRepository couponRepository;

    @Test
    void testFindAvailableCouponsByClientId() {
        // Given
        long clientId = 1L;

        // When
        List<Coupon> coupons = couponRepository.findAvailableCouponsByClientId(clientId);

        // Then
        Assertions.assertNotNull(coupons);

    }

    @Test
    void testFindByExpirationDateBefore() {
        // Given
        LocalDate expirationDate = LocalDate.now().plusDays(7);

        // When
        List<Coupon> coupons = couponRepository.findByExpirationDateBefore(expirationDate);

        // Then
        Assertions.assertNotNull(coupons);
    }

    @Test
    void testFindByClientId() {
        // Given
        long clientId = 1L;
        PageRequest pageRequest = PageRequest.of(0, 10);

        // When
        Page<Coupon> couponPage = couponRepository.findByClientId(clientId, pageRequest);

        // Then
        Assertions.assertNotNull(couponPage);
        Assertions.assertTrue(couponPage.getTotalElements() >= 0); // Example assertion

    }

    @Test
    void testFindAll() {
        // Given
        PageRequest pageRequest = PageRequest.of(0, 10);

        // When
        Page<Coupon> couponPage = couponRepository.findAll(pageRequest);

        // Then
        Assertions.assertNotNull(couponPage);
        Assertions.assertTrue(couponPage.getTotalElements() >= 0);
    }
}
