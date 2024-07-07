package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.dto.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.ProductCategoryCouponRepository;

import com.service.servicecoupon.repository.ProductCouponRepository;
import com.service.servicecoupon.service.impl.CouponPolicyServiceImpl;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CouponPolicyServiceTest {

    @Mock
    private CouponPolicyRepository couponPolicyRepository;

    @Mock
    private ProductCouponRepository productCouponRepository;

    @Mock
    private ProductCategoryCouponRepository productCategoryCouponRepository;

    @InjectMocks
    private CouponPolicyServiceImpl couponPolicyService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveProductCoupon() {
        // Given
        CouponPolicyRegisterRequestDto requestDto = new CouponPolicyRegisterRequestDto(
            "description", DiscountType.AMOUNTDISCOUNT, 10000, 50000, 100000, 1L, "상품");
        // When
        couponPolicyService.save(requestDto);

        // Then
        verify(couponPolicyRepository).save(any(CouponPolicy.class));
        verify(productCouponRepository).save(any(ProductCoupon.class));
    }

    @Test
    void testSaveProductCategoryCoupon() {
        // Given
        CouponPolicyRegisterRequestDto requestDto = new CouponPolicyRegisterRequestDto(
            "description", DiscountType.AMOUNTDISCOUNT, 10000, 50000, 100000, 1L, "카테고리");

        // When
        couponPolicyService.save(requestDto);

        // Then
        verify(couponPolicyRepository).save(any(CouponPolicy.class));
        verify(productCategoryCouponRepository).save(any(ProductCategoryCoupon.class));
    }

    @Test
    void testSaveCouponPolicy() {
        CouponPolicyRegisterRequestDto requestDto = new CouponPolicyRegisterRequestDto(
            "description", DiscountType.AMOUNTDISCOUNT, 10000, 50000, 100000, -1L, null);

        couponPolicyService.save(requestDto);

        verify(couponPolicyRepository).save(any(CouponPolicy.class));
    }

    @Test
    void testGetPolicies() {
        // Given
        CouponPolicy couponPolicy = new CouponPolicy(1L, "description",
            DiscountType.PERCENTAGEDISCOUNT, 10, 10000, 20000);

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("couponPolicyId").descending());
        List<CouponPolicy> couponPolicies = Collections.singletonList(couponPolicy);
        Page<CouponPolicy> couponPolicyPage = new PageImpl<>(couponPolicies, pageRequest, 1);

        when(couponPolicyRepository.findAll(pageRequest)).thenReturn(couponPolicyPage);

        // When
        Page<CouponPolicyListResponseDto> resultPage = couponPolicyService.getPolicies(0, 10);

        // Then
        assertEquals(1, resultPage.getContent().size());
        assertEquals(couponPolicy.getCouponPolicyId(),
            resultPage.getContent().getFirst().getCouponPolicyId());
        assertEquals(couponPolicy.getCouponPolicyDescription(),
            resultPage.getContent().getFirst().getCouponPolicyDescription());
        assertEquals(couponPolicy.getDiscountType().name(),
            resultPage.getContent().getFirst().getDiscountType());
        assertEquals(couponPolicy.getDiscountValue(),
            resultPage.getContent().getFirst().getDiscountValue());
        assertEquals(couponPolicy.getMinPurchaseAmount(),
            resultPage.getContent().getFirst().getMinPurchaseAmount());
        assertEquals(couponPolicy.getMaxDiscountAmount(),
            resultPage.getContent().getFirst().getMaxDiscountAmount());
    }
}
