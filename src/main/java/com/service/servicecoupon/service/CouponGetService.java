package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponGetService {

    private final CouponRewardContext couponRewardContext;
    private final CouponTimeContext couponTimeContext;

    public CouponPolicy getCouponRewardContext(String methodName) {
        return couponRewardContext.getPolicy(methodName);
    }

    public Integer getCouponTImeContext(String methodName) {
        return couponTimeContext.getTime(methodName);
    }

}
