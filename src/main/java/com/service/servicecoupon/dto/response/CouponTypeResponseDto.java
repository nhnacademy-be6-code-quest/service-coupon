package com.service.servicecoupon.dto.response;

import com.service.servicecoupon.domain.CouponKind;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponTypeResponseDto {
    long couponTypeId;
    CouponKind couponKind;

}
