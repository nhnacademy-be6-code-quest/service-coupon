package com.service.servicecoupon.controller;


import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.service.CouponService;
import com.service.servicecoupon.service.impl.CouponServiceImpl;
import jakarta.ws.rs.Path;
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
    public ResponseEntity<CouponRequestDto> saveCoupon(@PathVariable long couponPolicyId, @RequestBody CouponRequestDto couponRequest) {
        couponService.save(couponRequest,couponPolicyId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(couponRequest);
    }

    @PutMapping("/api/coupon/update/{couponId}")
    public ResponseEntity<CouponResponseDto> updateCoupon(@PathVariable long couponId){
        couponService.update(couponId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/client/welcome/{clientId}")
    public ResponseEntity<CouponResponseDto> payWelcomeCoupon(@PathVariable long clientId){
        couponService.payWelcomeCoupon(clientId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
