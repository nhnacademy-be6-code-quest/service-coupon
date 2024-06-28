package com.service.servicecoupon.controller.impl;

import com.service.servicecoupon.controller.CouponPolicyController;
import com.service.servicecoupon.domain.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import com.service.servicecoupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CouponPolicyControllerImpl implements CouponPolicyController {
    private final CouponPolicyService couponPolicyService;

    @Override
    @GetMapping("/api/coupon/policy")
    public Page<CouponPolicyResponseDto> findAllCouponPolicy(Pageable pageable){
        return couponPolicyService.getPolicies(pageable);
    }

    @Override
    @PostMapping("/api/coupon/policy/register")
    public ResponseEntity<CouponPolicyRegisterRequestDto> saveCouponPolicy(@RequestBody CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDto){
       couponPolicyService.save(couponPolicyRegisterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(couponPolicyRegisterRequestDto);
    }
}

