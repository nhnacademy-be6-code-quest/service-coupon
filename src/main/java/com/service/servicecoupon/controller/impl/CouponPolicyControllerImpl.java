package com.service.servicecoupon.controller.impl;

import com.service.servicecoupon.controller.CouponPolicyController;
import com.service.servicecoupon.domain.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class CouponPolicyControllerImpl implements CouponPolicyController{
    private final CouponPolicyService couponPolicyService;

//    @Override
    @GetMapping("/api/coupon/policy")
    public ResponseEntity<Page<CouponPolicyListResponseDto>> findAllCouponPolicy(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return ResponseEntity.ok(couponPolicyService.getPolicies(page, size));

    }



//    @Override
    @PostMapping("/api/coupon/policy/register")
    public ResponseEntity<CouponPolicyRegisterRequestDto> saveCouponPolicy(@RequestBody CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDto){
       couponPolicyService.save(couponPolicyRegisterRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(couponPolicyRegisterRequestDto);
    }
}

