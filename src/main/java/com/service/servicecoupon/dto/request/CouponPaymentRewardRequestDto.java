package com.service.servicecoupon.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CouponPaymentRewardRequestDto {
    Long clientId;
    Long paymentValue;
}
