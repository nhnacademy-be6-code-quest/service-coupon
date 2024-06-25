package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.response.CouponTypeResponseDto;
import com.service.servicecoupon.service.CouponTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponTypeControllerImpl {
    private final CouponTypeService couponTypeService;

    @GetMapping("/api/coupon/type")
    public List<CouponTypeResponseDto> findAllType(){
        return couponTypeService.findAllCouponType();
    }
}
