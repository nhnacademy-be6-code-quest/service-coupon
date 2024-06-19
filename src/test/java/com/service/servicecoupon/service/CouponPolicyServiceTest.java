package com.service.servicecoupon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.repository.CouponPolicyRepository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponPolicyServiceTest {
    @InjectMocks
    private CouponPolicyService couponPolicyService;
    @Mock
    private CouponPolicyRepository couponPolicyRepository;

    private CouponPolicy couponPolicy;
    @BeforeEach
    public void setUp() {
        couponPolicy = new CouponPolicy();
        couponPolicy.setCouponPolicyId(1L);
        couponPolicy = new CouponPolicy();
        couponPolicy.setCouponPolicyId(1L);
        couponPolicy.setProductId(1L);
        couponPolicy.setCouponPolicyDescription("description");
        couponPolicy.setDiscountType(DiscountType.AMOUNTDISCOUNT);
        couponPolicy.setMinPurchaseAmount(0);
        couponPolicy.setMaxDiscountAmount(10000);
    }
    @Test
    public void testSaveCoupon() {
        // Given
        when(couponPolicyRepository.save(any())).thenReturn(couponPolicy);

        // When
        CouponPolicy savedCoupon = couponPolicyService.save(couponPolicy);

        // Then
        assertEquals(couponPolicy, savedCoupon);
    }

    @Test
    public void testFindCouponById() {

        // Given
        long couponPolicyId = 1L;
        when(couponPolicyRepository.findById(couponPolicyId)).thenReturn(Optional.of(couponPolicy));

        // When
        CouponPolicy foundCouponPolicy = couponPolicyService.findById(couponPolicyId);

        // Then
        assertEquals(couponPolicy, foundCouponPolicy);
    }

    @Test
    public void testFindAllCoupon(){
        // Given
        List<CouponPolicy> couponPolicies = new ArrayList<>();
        couponPolicies.add(couponPolicy);
        when(couponPolicyService.findAllCouponPolicy()).thenReturn(couponPolicies);

        //When
        couponPolicyService.save(couponPolicy);
        List<CouponPolicy> couponList= couponPolicyService.findAllCouponPolicy();

        //Then
        Assertions.assertThat(couponList.size()).isEqualTo(1);
    }
    @Test
    public void testDeleteCoupon() {
        //Given
        long couponPolicyId = 1L;
        couponPolicyService.save(couponPolicy);
        //when
        couponPolicyService.deleteById(couponPolicyId);
        //Then
        Assertions.assertThat(couponPolicyService.findById(couponPolicyId)).isNull();

    }
}
