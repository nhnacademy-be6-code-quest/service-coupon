package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.request.CouponPolicyRequestDto;
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
public class CouponPolicyController {
    private final CouponPolicyService couponPolicyService;

    @GetMapping("/admin/coupon/policy/{couponPolicyId}")
    public CouponPolicyResponseDto findCouponPolicy(@PathVariable long couponPolicyId){
        return couponPolicyService.getPolicy(couponPolicyId);
    }

    @GetMapping("/admin/coupon/policy")
    public Page<CouponPolicyResponseDto> findAllCouponPolicy(Pageable pageable){
        return couponPolicyService.getPolicies(pageable);
    }

    @PostMapping("/admin/coupon/policy/register")
    public ResponseEntity<CouponPolicyRequestDto> saveCouponPolicy(@RequestBody CouponPolicyRequestDto couponPolicyRequestDto){
       couponPolicyService.save(couponPolicyRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(couponPolicyRequestDto);
    }
}

