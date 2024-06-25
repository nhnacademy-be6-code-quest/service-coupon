package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.request.CouponPolicyRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Client", description = "유저 관련 API")
public interface CouponPolicyController {
    @Operation(
            summary = "회원가입",
            description = "Auth - 사용자를 등록",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "회원가입 시간 및 회원 이메일 반환"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "이메일 중복시 반환"
                    )
            }
    )
    @GetMapping("/api/coupon/policy/{couponPolicyId}")
    CouponPolicyResponseDto findCouponPolicy(@PathVariable long couponPolicyId);

    @GetMapping("/api/coupon/policy")
    Page<CouponPolicyResponseDto> findAllCouponPolicy(Pageable pageable);

    @PostMapping("/api/coupon/policy/register")
    ResponseEntity<CouponPolicyRequestDto> saveCouponPolicy(@RequestBody CouponPolicyRequestDto couponPolicyRequestDto);
}
