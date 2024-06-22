package com.service.servicecoupon.service;

import java.util.Arrays;
import java.util.List;

import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.repository.CouponTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponTypeServiceTest {

    @InjectMocks
    private CouponTypeService couponTypeService;

    @Mock
    private CouponTypeRepository couponTypeRepository;

    private List<CouponType> couponTypes;

    @BeforeEach
    public void setUp() {
        CouponType couponType1 = new CouponType(1L, CouponKind.DISCOUNTCOUPON);
        CouponType couponType2 = new CouponType(2L, CouponKind.BOOKCOUPON);
        couponTypes = Arrays.asList(couponType1, couponType2);
    }

    @Test
    public void testFindAllCouponType() {
        // Given
        when(couponTypeRepository.findAll()).thenReturn(couponTypes);

        // When
        List<CouponType> foundCouponTypes = couponTypeService.findAllCouponType();

        // Then
        assertEquals(couponTypes.size(), foundCouponTypes.size());
    }
}
