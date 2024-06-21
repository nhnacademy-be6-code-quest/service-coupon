package com.service.servicecoupon.domain.response;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CouponResponseDto {
    private long couponId;
    private CouponType couponType;
    private CouponPolicy couponPolicy;
    private LocalDateTime issuedDate;
    private LocalDateTime expirationDate;
    private LocalDateTime usedDate;
    private Status status;
}
