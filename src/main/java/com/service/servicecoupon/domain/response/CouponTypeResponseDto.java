package com.service.servicecoupon.domain.response;

import com.service.servicecoupon.domain.CouponKind;

public record CouponTypeResponseDto(long couponTypeId, CouponKind couponKind) {
}
