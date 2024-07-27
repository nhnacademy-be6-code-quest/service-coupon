package com.service.servicecoupon.dto.response;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentCompletedCouponResponseDtoTest {

    @Test
    void testNoArgsConstructor() {
        // Test that the no-args constructor initializes the object
        PaymentCompletedCouponResponseDto dto = new PaymentCompletedCouponResponseDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getCouponId()).isNull();
    }

    @Test
    void testGetterAndSetter() {
        // Test that the setter sets the correct value and the getter retrieves it
        PaymentCompletedCouponResponseDto dto = new PaymentCompletedCouponResponseDto();
        Long couponId = 123L;
        dto.setCouponId(couponId);
        assertThat(dto.getCouponId()).isEqualTo(couponId);
    }
}
