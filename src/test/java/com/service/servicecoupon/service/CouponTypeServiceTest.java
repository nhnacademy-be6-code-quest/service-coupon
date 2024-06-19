//package com.service.servicecoupon.service;
//
//import com.service.servicecoupon.domain.CouponKind;
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
//    private CouponType couponTypeId;
//
//    @BeforeEach
//    public void setUp() {
//        couponTypeId = new CouponType();
//        couponTypeId.setCouponTypeId(1L);
//        couponTypeId.setCouponKind(CouponKind.BOOKCOUPON);
//    }
//
//    @Test
//    public void testSaveCoupon() {
//        // Given
//        when(couponTypeRepository.save(any())).thenReturn(couponTypeId);
//
//        // When
//        CouponType savedCoupon = couponTypeService.save(couponTypeId);
//
//        // Then
//        assertEquals(couponTypeId, savedCoupon);
//    }
//
//    @Test
//    public void testFindCouponById() {
//        // Given
//        long couponTypeId = 1L;
//        when(couponTypeRepository.findById(couponTypeId)).thenReturn(Optional.of(couponTypeId));
//
//        // When
//        CouponType foundCouponType = couponTypeService.findById(couponTypeId);
//
//        // Then
//        assertEquals(couponTypeId, foundCouponType);
//    }
//
//    @Test
//    public void testFindAllCoupon() {
//        // Given
//        List<Coupon> sampleCoupon=new ArrayList<>();
//        List<CouponType> couponTypeLists = new ArrayList<>();
//        CouponType couponType2=new CouponType(2L,CouponKind.DISCOUNTCOUPON,sampleCoupon);
//        couponTypeLists.add(couponTypeId);
//        couponTypeLists.add(couponType2);
//
//        when(couponTypeRepository.findAll()).thenReturn(couponTypeLists);
//
//        // When
//        couponTypeService.save(couponTypeId);
//        couponTypeService.save(couponType2);
//        List<CouponType> couponTypeList = couponTypeService.findAllCouponType();
//
//        // Then
//        Assertions.assertThat(couponTypeList.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void testDeleteCoupon() {
//        long couponTypeId = 1L;
//
//        couponTypeService.save(couponTypeId);
//
//        // When
//        couponTypeService.deleteCouponType(couponTypeId);
//
//        // Then
//        Assertions.assertThat(couponTypeService.findById(couponTypeId)).isNull();
//    }
//}
