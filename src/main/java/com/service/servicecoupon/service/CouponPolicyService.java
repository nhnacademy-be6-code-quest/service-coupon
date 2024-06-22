package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.request.CouponPolicyRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponPolicyService {
    void save(CouponPolicyRequestDto couponPolicyRequestDto);

    CouponPolicy findById(long couponPolicyId);

    Page<CouponPolicyResponseDto> getPolicies(Pageable pageable);


    CouponPolicyResponseDto getPolicy(long couponPolicyId);
}
