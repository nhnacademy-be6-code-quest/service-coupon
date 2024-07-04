package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CouponPolicyService {
    void save(CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDt);



    Page<CouponPolicyListResponseDto> getPolicies(int page, int size);


}
