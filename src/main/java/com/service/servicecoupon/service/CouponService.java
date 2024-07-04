package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponOrderResponseDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CouponService {

    void save(CouponRequestDto couponRequest, long couponPolicyId);

    List<CouponOrderResponseDto> findClientCoupon(HttpHeaders httpHeaders);

        Page<CouponResponseDto> findByClientId(HttpHeaders httpHeaders, int page, int size);

    void update(long couponId);

    void payWelcomeCoupon(long clientId);

    void refundCoupon(long couponId);


}
