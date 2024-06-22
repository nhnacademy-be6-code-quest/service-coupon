package com.service.servicecoupon.controller;


import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.service.CouponService;
import com.service.servicecoupon.service.impl.CouponServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;


    @GetMapping("/api/coupon/{clientId}")
    public List<CouponResponseDto> couponFind(@PathVariable long clientId) {
        return couponService.findByClientId(clientId);
    }

    @PostMapping("/api/coupon/register/{couponPolicyId}")
    public ResponseEntity<CouponRequestDto> saveCoupon(@PathVariable long couponPolicyId,@RequestBody CouponRequestDto couponRequest) {
        couponService.save(couponRequest,couponPolicyId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
