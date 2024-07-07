package com.service.servicecoupon.controller.impl;

import com.service.servicecoupon.controller.CouponTypeController;
import com.service.servicecoupon.dto.response.CouponTypeResponseDto;
import com.service.servicecoupon.service.CouponTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponTypeControllerImpl implements CouponTypeController {
    private final CouponTypeService couponTypeService;

    @Override
    @GetMapping("/api/coupon/type")
    public List<CouponTypeResponseDto> findAllType(){
        return couponTypeService.findAllCouponType();
    }
}
