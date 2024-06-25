package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.response.CouponTypeResponseDto;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CouponTypeController {
    @GetMapping("/api/coupon/type")
    List<CouponTypeResponseDto> findAllType();
}
