package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;

    @Mock
    private CouponRepository couponRepository;

    private Coupon coupon;

    @BeforeEach
    public void setUp() {
        CouponType couponType = new CouponType();
        couponType.setCouponTypeId(1L);
        CouponPolicy couponPolicy = new CouponPolicy();
        couponPolicy.setCouponPolicyId(1L);
        coupon = new Coupon();
        coupon.setCouponId(1L);
        coupon.setCouponType(couponType);
        coupon.setCouponPolicy(couponPolicy);
        coupon.setClientId(123L);
        coupon.setIssuedDate(new Date());
        coupon.setExpirationDate(new Date());
        coupon.setUsedDate(new Date());
        coupon.setStatus(Status.AVAILABLE);
    }

    @Test
    public void testSaveCoupon() {
        // Given
        when(couponRepository.save(any())).thenReturn(coupon);

        // When
        Coupon savedCoupon = couponService.save(coupon);

        // Then
        assertEquals(coupon, savedCoupon);
    }

    @Test
    public void testFindCouponById() {
        // Given
        long couponId = 1L;
        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));

        // When
        Coupon foundCoupon = couponService.findById(couponId);

        // Then
        assertEquals(coupon, foundCoupon);
    }

    @Test
    public void testFindAllCoupon() {
        // Given
        List<Coupon> coupons = new ArrayList<>();
        coupons.add(coupon);
        when(couponRepository.findAll()).thenReturn(coupons);

        // When
        couponService.save(coupon);
        List<Coupon> couponList = couponService.findAllCoupon();

        // Then
        Assertions.assertThat(couponList.size()).isEqualTo(1);
    }

    @Test
    public void testDeleteCoupon() {
        long couponId = 1L;

        couponService.save(coupon);

        // When
        couponService.delete(couponId);

        // Then
        Assertions.assertThat(couponService.findById(couponId)).isNull();
    }
}
