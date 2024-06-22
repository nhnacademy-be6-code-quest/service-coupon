package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;

import java.util.List;

public interface CouponService {

    void save(CouponRequestDto couponRequest, long couponPolicyId);

    List<CouponResponseDto> findByClientId(long clientId);


}
