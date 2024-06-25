package com.service.servicecoupon.domain.request;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import com.service.servicecoupon.domain.response.CouponTypeResponseDto;

import java.time.LocalDateTime;

public record CouponRequestDto(long couponTypeId, long couponPolicyId, long clientId, LocalDateTime expirationDate, Status status) {
}
