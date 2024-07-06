package com.service.servicecoupon.dto.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.service.servicecoupon.domain.Status;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

class CouponRequestDtoTest {

    private final Validator validator;

    public CouponRequestDtoTest() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidCouponRequestDto() {
        CouponRequestDto dto = new CouponRequestDto(
            1L,
            2L,
            List.of(1L, 2L, 3L),
            LocalDate.of(2023, 12, 31),
            Status.AVAILABLE
        );

        Set<ConstraintViolation<CouponRequestDto>> violations = validator.validate(dto);
        assertEquals(0, violations.size());
    }

    @Test
    void testInvalidCouponRequestDto() {
        CouponRequestDto dto = new CouponRequestDto(
            1L,
            2L,
            null,  // clientId is null
            null,  // expirationDate is null
            null   // status is null
        );

        Set<ConstraintViolation<CouponRequestDto>> violations = validator.validate(dto);
        assertEquals(3, violations.size());
    }

    @Test
    void testFieldValues() {
        long couponTypeId = 1L;
        long couponPolicyId = 2L;
        List<Long> clientId = List.of(1L, 2L, 3L);
        LocalDate expirationDate = LocalDate.of(2023, 12, 31);
        Status status = Status.UNAVAILABLE;

        CouponRequestDto dto = new CouponRequestDto(
            couponTypeId,
            couponPolicyId,
            clientId,
            expirationDate,
            status
        );

        assertEquals(couponTypeId, dto.couponTypeId());
        assertEquals(couponPolicyId, dto.couponPolicyId());
        assertEquals(clientId, dto.clientId());
        assertEquals(expirationDate, dto.expirationDate());
        assertEquals(status, dto.status());
    }
}
