//package com.service.servicecoupon.service;
//
//import com.service.servicecoupon.domain.Status;
//import com.service.servicecoupon.domain.entity.Coupon;
//import com.service.servicecoupon.domain.entity.CouponPolicy;
//import com.service.servicecoupon.domain.entity.CouponType;
//import com.service.servicecoupon.repository.CouponRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.platform.engine.TestExecutionResult;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CouponServiceTest {
//
//    @InjectMocks
//    private CouponService couponService;
//
//    @Mock
//    private CouponRepository couponRepository;
//
//    private Coupon coupon;
//
//    @BeforeEach
//    public void setUp() {
//        CouponType couponTypeId = new CouponType();
//        couponTypeId.setCouponTypeId(1L);
//        CouponPolicy couponPolicyId = new CouponPolicy();
//        couponPolicyId.setCouponPolicyId(1L);
//        coupon = new Coupon();
//        coupon.setCouponId(1L);
//        coupon.setCouponType(couponTypeId);
//        coupon.setCouponPolicy(couponPolicyId);
//        coupon.setClientId(123L);
//        coupon.setIssuedDate(new Date());
//        coupon.setExpirationDate(new Date());
//        coupon.setUsedDate(new Date());
//        coupon.setStatus(Status.AVAILABLE);
//    }
//
//    @Test
//    public void testSaveCoupon() {
//        // Given
//        when(couponRepository.save(any())).thenReturn(coupon);
//
//        // When
//        Coupon savedCoupon = couponService.save(coupon);
//
//        // Then
//        assertEquals(coupon, savedCoupon);
//    }
//
//    @Test
//    public void testFindCouponById() {
//        // Given
//        long couponId = 1L;
//        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));
//
//        // When
//        Coupon foundCoupon = couponService.findById(couponId);
//
//        // Then
//        assertEquals(coupon.getCouponId(), foundCoupon.getCouponId());
//        assertEquals(coupon.getCouponPolicy(), foundCoupon.getCouponPolicy());
//        assertEquals(coupon.getCouponType(), foundCoupon.getCouponType());
//        assertEquals(coupon.getClientId(), foundCoupon.getClientId());
//        assertEquals(coupon.getIssuedDate(), foundCoupon.getIssuedDate());
//        assertEquals(coupon.getExpirationDate(), foundCoupon.getExpirationDate());
//        assertEquals(coupon.getUsedDate(), foundCoupon.getUsedDate());
//        assertEquals(coupon.getStatus(), foundCoupon.getStatus());
//
//    }
//
//    @Test
//    public void testFindAllCoupon() {
//        // Given
//        Coupon coupon2=new Coupon(2L, new CouponType(), new CouponPolicy(),2L, new Date(), new Date(), new Date(), Status.USED);
//        List<Coupon> coupons = new ArrayList<>();
//        coupons.add(coupon);
//        coupons.add(coupon2);
//        when(couponRepository.findAll()).thenReturn(coupons);
//
//        // When
//        couponService.save(coupon);
//        couponService.save(coupon2);
//        List<Coupon> couponList = couponService.findAllCoupon();
//
//        // Then
//        Assertions.assertThat(couponList.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void testDeleteCoupon() {
//        long couponId = 1L;
//
//        couponService.save(coupon);
//
//        // When
//        couponService.delete(couponId);
//
//        // Then
//        Assertions.assertThat(couponService.findById(couponId)).isNull();
//    }
//}
