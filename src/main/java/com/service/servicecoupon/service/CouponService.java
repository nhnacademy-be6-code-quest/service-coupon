package com.service.servicecoupon.service;

import com.service.servicecoupon.dto.request.CouponRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.PaymentCompletedCouponResponseDto;
import com.service.servicecoupon.dto.response.RefundCouponResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CouponService {

    void save(CouponRequestDto couponRequest, long couponPolicyId);

    List<CouponOrderResponseDto> findClientCoupon(HttpHeaders httpHeaders);

    Page<CouponMyPageCouponResponseDto> findByClientId(HttpHeaders httpHeaders, int page, int size);

    Page<CouponAdminPageCouponResponseDto> findByAllCoupon(int page, int size);

    void paymentCompletedCoupon(
        PaymentCompletedCouponResponseDto paymentCompletedCouponResponseDto);

    void payWelcomeCoupon(String message);

    void refundCoupon(RefundCouponResponseDto refundCouponResponseDto);


}
