package com.service.servicecoupon.domain.response;

import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.entity.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CouponTypeResponseDto {
    long couponTypeId;
    CouponKind couponKind;

}
