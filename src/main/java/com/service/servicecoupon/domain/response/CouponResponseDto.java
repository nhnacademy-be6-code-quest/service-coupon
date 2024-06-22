package com.service.servicecoupon.domain.response;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponResponseDto {
    private long couponId;
    private CouponType couponType;
    private CouponPolicy couponPolicy;
    private LocalDateTime issuedDate;
    private long clientId;
    private LocalDateTime expirationDate;
    private LocalDateTime usedDate;
    private Status status;
}
