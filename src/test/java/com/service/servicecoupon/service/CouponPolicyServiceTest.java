package com.service.servicecoupon.service;

import java.util.Optional;

import com.service.servicecoupon.domain.request.CouponPolicyRequestDto;
import com.service.servicecoupon.service.impl.CouponPolicyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.repository.CouponPolicyRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CouponPolicyServiceTest {
    @InjectMocks
    private CouponPolicyServiceImpl couponPolicyService;
    @Mock
    private CouponPolicyRepository couponPolicyRepository;

    private CouponPolicy couponPolicy;
    private CouponPolicyRequestDto couponPolicyRequestDto;

    @BeforeEach
    public void setUp() {
        couponPolicy = new CouponPolicy();
        couponPolicy.setCouponPolicyId(1L);
        couponPolicy.setProductId(1L);
        couponPolicy.setCouponPolicyDescription("description");
        couponPolicy.setDiscountType(DiscountType.AMOUNTDISCOUNT);
        couponPolicy.setMinPurchaseAmount(0);
        couponPolicy.setMaxDiscountAmount(10000);

        couponPolicyRequestDto = new CouponPolicyRequestDto(1L, 1L, "description", DiscountType.AMOUNTDISCOUNT, 0, 10000, 10000);
    }

    @Test
    public void testSaveCoupon() {
        // Given
        when(couponPolicyRepository.save(any(CouponPolicy.class))).thenReturn(couponPolicy);

        // When
        couponPolicyService.save(couponPolicyRequestDto);

        // Then
        verify(couponPolicyRepository, times(1)).save(any(CouponPolicy.class));
    }

    @Test
    public void testFindCouponById() {
        // Given
        long couponPolicyId = 1L;
        when(couponPolicyRepository.findById(couponPolicyId)).thenReturn(Optional.of(couponPolicy));

        // When
        CouponPolicy foundCouponPolicy = couponPolicyService.findById(couponPolicyId);

        // Then
        assertEquals(couponPolicy.getCouponPolicyId(), foundCouponPolicy.getCouponPolicyId());
        assertEquals(couponPolicy.getProductId(), foundCouponPolicy.getProductId());
        assertEquals(couponPolicy.getCouponPolicyDescription(), foundCouponPolicy.getCouponPolicyDescription());
        assertEquals(couponPolicy.getDiscountType(), foundCouponPolicy.getDiscountType());
        assertEquals(couponPolicy.getMinPurchaseAmount(), foundCouponPolicy.getMinPurchaseAmount());
        assertEquals(couponPolicy.getMaxDiscountAmount(), foundCouponPolicy.getMaxDiscountAmount());
    }
}
