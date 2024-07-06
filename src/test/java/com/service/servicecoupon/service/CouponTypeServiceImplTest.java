package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.dto.response.CouponTypeResponseDto;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.impl.CouponTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CouponTypeServiceImplTest {

    @Mock
    private CouponTypeRepository couponTypeRepository;

    @InjectMocks
    private CouponTypeServiceImpl couponTypeService;

    @BeforeEach
    public void setUp() {
        // Mock 데이터 설정
        CouponType couponType1 = new CouponType(1L, CouponKind.BIRTHDAY);
        CouponType couponType2 = new CouponType(2L, CouponKind.WELCOME);
        List<CouponType> couponTypeList = Arrays.asList(couponType1, couponType2);

        when(couponTypeRepository.findAll()).thenReturn(couponTypeList);
    }

    @Test
    void testFindAllCouponType() {
        // 테스트할 메서드 호출
        List<CouponTypeResponseDto> result = couponTypeService.findAllCouponType();

        // 기대하는 결과 설정
        List<CouponTypeResponseDto> expected = Arrays.asList(
            new CouponTypeResponseDto(1L, CouponKind.BIRTHDAY),
            new CouponTypeResponseDto(2L, CouponKind.WELCOME)
        );

        // 결과 비교
        assertEquals(expected.size(), result.size());

    }
}
