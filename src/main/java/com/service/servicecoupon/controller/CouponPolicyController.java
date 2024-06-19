package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.request.CouponPolicyRequest;
import com.service.servicecoupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponPolicyController {
    private final CouponPolicyService couponPolicyService;

    @GetMapping("/admin/coupon/policy/{couponPolicyId}")
    public CouponPolicy findCouponPolicy(@PathVariable long couponPolicyId){
        return couponPolicyService.findById(couponPolicyId);
    }

    @GetMapping("/admin/coupon/policy")
    public List<CouponPolicy> findAllCouponPolicy(){
        return couponPolicyService.findAllCouponPolicy();
    }

    @PostMapping("/admin/coupon/policy/register")
    public CouponPolicy saveCouponPolicy(@RequestBody CouponPolicyRequest couponPolicyRequest){
        return couponPolicyService.save(couponPolicyRequest);
    }
}

