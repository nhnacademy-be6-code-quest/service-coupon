package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.response.CouponTypeResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Tag(name = "CouponType", description = "쿠폰 타입 API")

public interface CouponTypeController {

    @Operation(
            summary = "쿠폰 타입 조회",
            description = "Admin - 쿠폰 타입 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 타입 정보 반환"
                    )
            }
    )
    @GetMapping("/api/coupon/type")
    List<CouponTypeResponseDto> findAllType();
}
