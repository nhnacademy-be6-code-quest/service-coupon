package com.service.servicecoupon.domain.request;

import com.service.servicecoupon.domain.Status;

import java.time.LocalDateTime;

public record CouponRequest(Long couponTypeId, Long couponPolicyId, long clientId, LocalDateTime expirationDate, Status status) {
}
