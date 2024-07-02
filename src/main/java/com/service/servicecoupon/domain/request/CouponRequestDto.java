package com.service.servicecoupon.domain.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import com.service.servicecoupon.domain.response.CouponTypeResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public record CouponRequestDto(
        long couponTypeId,
        long couponPolicyId,
        List<Long> clientId,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime expirationDate,
        Status status) {
}
