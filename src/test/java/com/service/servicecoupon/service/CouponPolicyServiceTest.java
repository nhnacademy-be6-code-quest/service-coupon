//package com.service.servicecoupon.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import com.service.servicecoupon.domain.entity.Coupon;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import com.service.servicecoupon.domain.DiscountType;
//import com.service.servicecoupon.domain.entity.CouponPolicy;
//import com.service.servicecoupon.repository.CouponPolicyRepository;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class CouponPolicyServiceTest {
//    @InjectMocks
//    private CouponPolicyService couponPolicyService;
//    @Mock
//    private CouponPolicyRepository couponPolicyRepository;
//
//    private CouponPolicy couponPolicyId;
//    @BeforeEach
//    public void setUp() {
//        couponPolicyId = new CouponPolicy();
//        couponPolicyId.setCouponPolicyId(1L);
//        couponPolicyId = new CouponPolicy();
//        couponPolicyId.setCouponPolicyId(1L);
//        couponPolicyId.setProductId(1L);
//        couponPolicyId.setCouponPolicyDescription("description");
//        couponPolicyId.setDiscountType(DiscountType.AMOUNTDISCOUNT);
//        couponPolicyId.setMinPurchaseAmount(0);
//        couponPolicyId.setMaxDiscountAmount(10000);
//    }
//    @Test
//    public void testSaveCoupon() {
//        // Given
//        when(couponPolicyRepository.save(any())).thenReturn(couponPolicyId);
//
//        // When
//        CouponPolicy savedCouponPolicy = couponPolicyService.save(couponPolicyId);
//
//        // Then
//        assertEquals(couponPolicyId, savedCouponPolicy);
//    }
//
//    @Test
//    public void testFindCouponById() {
//
//        // Given
//        long couponPolicyId = 1L;
//        when(couponPolicyRepository.findById(couponPolicyId)).thenReturn(Optional.of(couponPolicyId));
//
//        // When
//        CouponPolicy foundCouponPolicy = couponPolicyService.findById(couponPolicyId);
//
//        // Then
//        assertEquals(couponPolicyId.getCouponPolicyId(), foundCouponPolicy.getCouponPolicyId());
//        assertEquals(couponPolicyId.getProductId(), foundCouponPolicy.getProductId());
//        assertEquals(couponPolicyId.getProductCategoryId(), foundCouponPolicy.getProductCategoryId());
//        assertEquals(couponPolicyId.getCouponPolicyDescription(), foundCouponPolicy.getCouponPolicyDescription());
//        assertEquals(couponPolicyId.getDiscountType(), foundCouponPolicy.getDiscountType());
//        assertEquals(couponPolicyId.getDiscountValue(), foundCouponPolicy.getDiscountValue());
//        assertEquals(couponPolicyId.getMinPurchaseAmount(), foundCouponPolicy.getMinPurchaseAmount());
//        assertEquals(couponPolicyId.getMaxDiscountAmount(), foundCouponPolicy.getMaxDiscountAmount());
//
//    }
//
//    @Test
//    public void testFindAllCoupon(){
//        // Given
//        List<Coupon> sampleCoupon=new ArrayList<>();
//        CouponPolicy couponPolicy2=new CouponPolicy(2L,null,null,"description",DiscountType.AMOUNTDISCOUNT,20000,0,50000,sampleCoupon);
//        List<CouponPolicy> couponPolicies = new ArrayList<>();
//        couponPolicies.add(couponPolicyId);
//        couponPolicies.add(couponPolicy2);
//        when(couponPolicyService.findAllCouponPolicy()).thenReturn(couponPolicies);
//
//        //When
//        couponPolicyService.save(couponPolicyId);
//        couponPolicyService.save(couponPolicy2);
//        List<CouponPolicy> couponList= couponPolicyService.findAllCouponPolicy();
//
//        //Then
//        Assertions.assertThat(couponList.size()).isEqualTo(2);
//    }
//    @Test
//    public void testDeleteCoupon() {
//        //Given
//        long couponPolicyId = 1L;
//        couponPolicyService.save(couponPolicyId);
//        //when
//        couponPolicyService.deleteById(couponPolicyId);
//        //Then
//        Assertions.assertThat(couponPolicyService.findById(couponPolicyId)).isNull();
//
//    }
//}
