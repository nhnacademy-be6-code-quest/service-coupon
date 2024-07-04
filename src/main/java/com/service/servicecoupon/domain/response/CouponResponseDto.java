package com.service.servicecoupon.domain.response;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CouponResponseDto {
    private long couponId;
    private CouponTypeResponseDto couponType;
    private CouponPolicyResponseDto couponPolicy;
    private LocalDate issuedDate;
    private long clientId;
    private LocalDate expirationDate;
    private LocalDate usedDate;
    private Status status;
}
