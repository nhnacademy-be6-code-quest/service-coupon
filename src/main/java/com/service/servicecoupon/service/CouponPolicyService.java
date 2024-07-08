package com.service.servicecoupon.service;

import com.service.servicecoupon.dto.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.dto.response.CouponProvideTypeResponseDto;
import org.springframework.data.domain.Page;

public interface CouponPolicyService {
    void save(CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDt);

    Page<CouponPolicyListResponseDto> getPolicies(int page, int size);

    CouponProvideTypeResponseDto findType(long couponPolicyId);
}
