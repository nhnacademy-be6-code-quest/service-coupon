package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.dto.request.CouponRequestDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.impl.CouponServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

    @InjectMocks
    private CouponServiceImpl couponService;

    @Mock
    private CouponTypeRepository couponTypeRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private CouponPolicyRepository couponPolicyRepository;
    private Coupon coupon;
    private CouponRequestDto couponRequestDto;

    @BeforeEach
    public void setUp() {
        CouponType couponTypeId = new CouponType(1L, CouponKind.BOOK);
        CouponPolicy couponPolicyId = new CouponPolicy();
        coupon = new Coupon();
        coupon.setCouponId(1L);
        coupon.setCouponType(couponTypeId);
        coupon.setCouponPolicy(couponPolicyId);
        coupon.setClientId(1L);
        coupon.setIssuedDate(LocalDateTime.now());
        coupon.setExpirationDate(LocalDateTime.now());
        coupon.setUsedDate(LocalDateTime.now());
        coupon.setStatus(Status.AVAILABLE);
    }

    @Test
    public void testSaveCoupon() {
        // Given
        couponRequestDto = new CouponRequestDto(1L, 1L, 1L, LocalDateTime.now().plusDays(10), Status.USED);
        when(couponTypeRepository.findById(1L)).thenReturn(Optional.of(coupon.getCouponType()));
        when(couponPolicyRepository.findById(1L)).thenReturn(Optional.of(coupon.getCouponPolicy()));
        when(couponRepository.save(any())).thenReturn(coupon);
        long couponPolicyId = 1L;
        // When
        couponService.save(couponRequestDto, couponPolicyId);

        // Then
        assertEquals(coupon.getClientId(), couponRequestDto.clientId());

    }


//    @JsonString
//    void testFindByClientId_whenCouponsExist() {
//        long clientId = 1L;
//        List<CouponResponseDto> mockCoupons = Arrays.asList(new CouponResponseDto(), new CouponResponseDto());
//
//        when(couponRepository.findByClientId(clientId)).thenReturn(mockCoupons);
//
//        List<CouponResponseDto> result = couponService.findByClientId(clientId);
//
//        assertNotNull(result);
//        assertEquals(2, result.size());
//
//    }





}
