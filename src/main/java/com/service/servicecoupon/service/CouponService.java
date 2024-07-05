package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponOrderResponseDto;
import com.service.servicecoupon.domain.response.CouponMyPageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CouponService {

    void save(CouponRequestDto couponRequest, long couponPolicyId);

    List<CouponOrderResponseDto> findClientCoupon(HttpHeaders httpHeaders);

    Page<CouponMyPageResponseDto> findByClientId(HttpHeaders httpHeaders, int page, int size);

    void update(long couponId);

    void payWelcomeCoupon(String message);

    void refundCoupon(long couponId);


}
