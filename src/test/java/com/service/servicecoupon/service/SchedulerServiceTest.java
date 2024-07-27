package com.service.servicecoupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.service.servicecoupon.client.BirthDayUserClient;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.impl.SchedulerServiceImpl;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class SchedulerServiceTest {
    @Mock
    private CouponTypeRepository couponTypeRepository;

    @Mock
    private CouponPolicyRepository couponPolicyRepository;

    @Mock
    private BirthDayUserClient birthDayUserClient;

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private SchedulerServiceImpl schedulerServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBirthCoupon() {
        // Arrange
        CouponType couponType = new CouponType();
        setField(couponType,"couponTypeId",1L);
        setField(couponType,"couponKind", CouponKind.BIRTHDAY);

        CouponPolicy couponPolicy = new CouponPolicy();
        setField(couponPolicy,"couponPolicyDescription","생일 쿠폰");

        when(couponTypeRepository.findByCouponKind(CouponKind.BIRTHDAY)).thenReturn(couponType);
        when(couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc("생일")).thenReturn(couponPolicy);

        List<Long> userIds = Arrays.asList(1L, 2L, 3L);
        when(birthDayUserClient.getThisMonthBirthClient()).thenReturn(userIds);

        // Act
        schedulerServiceImpl.birthCoupon();

        // Assert
        verify(couponRepository, times(3)).save(any(Coupon.class));
    }

}
