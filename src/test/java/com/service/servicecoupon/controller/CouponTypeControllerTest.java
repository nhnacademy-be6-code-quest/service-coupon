package com.service.servicecoupon.controller;

import com.service.servicecoupon.controller.CouponTypeController;
import com.service.servicecoupon.controller.impl.CouponTypeControllerImpl;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.dto.response.CouponTypeResponseDto;
import com.service.servicecoupon.service.CouponTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class CouponTypeControllerTest {

    @Mock
    private CouponTypeService couponTypeService;

    @InjectMocks
    private CouponTypeControllerImpl couponTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllType() {
        // Given
        List<CouponTypeResponseDto> expectedTypes = List.of(
            new CouponTypeResponseDto(1L, CouponKind.WELCOME),
            new CouponTypeResponseDto(2L, CouponKind.BIRTHDAY)
        );
        when(couponTypeService.findAllCouponType()).thenReturn(expectedTypes);

        // When
        ResponseEntity<List<CouponTypeResponseDto>> responseEntity = ResponseEntity.ok(couponTypeController.findAllType());

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<CouponTypeResponseDto> actualTypes = responseEntity.getBody();

        assertThat(actualTypes).hasSize(expectedTypes.size());
        assertThat(actualTypes).containsExactlyElementsOf(expectedTypes);
    }
}
