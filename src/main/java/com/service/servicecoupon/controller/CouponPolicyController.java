package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "CouponPolicy", description = "쿠폰 정책 API")
public interface CouponPolicyController {
    @Operation(
            summary = "쿠폰 정책 조회",
            description = "Admin - 쿠폰 정책 조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 정책 정보 반환"
                    )
            }
    )

    @GetMapping("/api/coupon/policy")
    Page<CouponPolicyResponseDto> findAllCouponPolicy(Pageable pageable); //TODO 변경예정

    @Operation(
            summary = "쿠폰 정책 등록",
            description = "Admin - 쿠폰 정책 등록",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 정책 정보 반환"
                    )
            }
    )
    @PostMapping("/api/coupon/policy/register")
    ResponseEntity<CouponPolicyRegisterRequestDto> saveCouponPolicy(
            @Parameter(description = "쿠폰 정책 정보")
            @RequestBody CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDto);
}
