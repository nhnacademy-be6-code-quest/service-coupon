//package com.service.servicecoupon.service;
//
//import com.service.servicecoupon.domain.entity.Coupon;
//import com.service.servicecoupon.domain.entity.CouponType;
//import com.service.servicecoupon.repository.CouponTypeRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CouponTypeServiceTest {
//
//    @InjectMocks
//    private CouponTypeService couponTypeService;
//
//    @Mock
//    private CouponTypeRepository couponTypeRepository;
//
//    private CouponType couponType;
//
//    @BeforeEach
//    public void setUp() {
//        couponType = new CouponType();
//        couponType.setCouponTypeId(1L);
//    }
//
//    @Test
//    public void testSaveCoupon() {
//        // Given
//        when(couponTypeRepository.save(any())).thenReturn(couponType);
//
//        // When
//        CouponType savedCoupon = couponTypeService.save(couponType);
//
//        // Then
//        assertEquals(couponType, savedCoupon);
//    }
//
//    @Test
//    public void testFindCouponById() {
//        // Given
//        long couponTypeId = 1L;
//        when(couponTypeRepository.findById(couponTypeId)).thenReturn(Optional.of(couponType));
//
//        // When
//        CouponType foundCouponType = couponTypeService.findById(couponTypeId);
//
//        // Then
//        assertEquals(couponType, foundCouponType);
//    }
//
//    @Test
//    public void testFindAllCoupon() {
//        // Given
//        List<CouponType> coupons = new ArrayList<>();
//        coupons.add(couponType);
//        when(couponTypeRepository.findAll()).thenReturn(coupons);
//
//        // When
//        couponTypeService.save(couponType);
//        List<CouponType> couponTypeList = couponTypeService.findAllCouponType();
//
//        // Then
//        Assertions.assertThat(couponTypeList.size()).isEqualTo(1);
//    }
//
//    @Test
//    public void testDeleteCoupon() {
//        long couponTypeId = 1L;
//
//        couponTypeService.save(couponType);
//
//        // When
//        couponTypeService.deleteCouponType(couponTypeId);
//
//        // Then
//        Assertions.assertThat(couponTypeService.findById(couponTypeId)).isNull();
//    }
//}
