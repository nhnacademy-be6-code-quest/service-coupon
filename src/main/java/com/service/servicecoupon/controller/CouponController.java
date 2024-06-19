package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.request.CouponRequest;
import com.service.servicecoupon.domain.response.CouponResponse;
import com.service.servicecoupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponController {

    private final CouponService couponService;


    @GetMapping("/coupon/{clientId}")
    public List<Coupon> couponFind(@PathVariable long clientId){
        return couponService.findByClientId(clientId);
    }

    @PostMapping("/admin/coupon/register")
    public Coupon saveCoupon( @RequestBody CouponRequest couponRequest){
        return couponService.save(couponRequest);
    }


    @PutMapping("/")
    public Coupon updateCoupon(@PathVariable long couponId){
        return couponService.updateCoupon(couponId);
    }

}
