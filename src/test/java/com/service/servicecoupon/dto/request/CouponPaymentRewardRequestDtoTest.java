package com.service.servicecoupon.dto.request;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CouponPaymentRewardRequestDtoTest {

    @Test
    void testNoArgsConstructor() {
        // Test that the no-args constructor initializes the object
        CouponPaymentRewardRequestDto dto = new CouponPaymentRewardRequestDto();
        assertThat(dto).isNotNull();
        assertThat(dto.getPaymentValue()).isNull();
    }

    @Test
    void testGetter() {
        // Test that the getter returns the correct value
        CouponPaymentRewardRequestDto dto = new CouponPaymentRewardRequestDto();
        Long paymentValue = 100L;
        dto.paymentValue = paymentValue; // Directly setting the field value for testing purposes
        assertThat(dto.getPaymentValue()).isEqualTo(paymentValue);
    }
}
