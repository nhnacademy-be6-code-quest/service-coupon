package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Coupon", description = "쿠폰 관련 API")
public interface CouponController {
    @Operation(
            summary = "쿠폰 조회",
            description = "MyPage, Payment - 사용자 쿠폰조회",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 정보 반환"
                    )
            }
    )
    @GetMapping("/api/coupon/{clientId}")
    List<CouponResponseDto> couponFind(
            @Parameter(description = "쿠폰을 조회하는 회원의 아이디")
            @PathVariable long clientId);

    @Operation(
            summary = "쿠폰 지급",
            description = "AdminPage - 사용자에게 쿠폰지급",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "쿠폰 정보 반환"
                    )
            }
    )
    @PostMapping("/api/coupon/register/{couponPolicyId}")
   ResponseEntity<CouponRequestDto> saveCoupon(
            @Parameter(description = "쿠폰정책 아이디")
            @PathVariable long couponPolicyId,
            @Parameter(description = "쿠폰 정보")
            @RequestBody CouponRequestDto couponRequest);

    @Operation(
            summary = "쿠폰 변경",
            description = "Payment - 결제시 쿠폰 사용일,상태 변경",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공 여부 반환"
                    )
            }
    )
    @PutMapping("/api/coupon/update")
    ResponseEntity<String> updateCoupon(
            @Parameter(description = "쿠폰 아이디")
            long couponId);

    @Operation(
            summary = "쿠폰 지급",
            description = " Auth - 회원가입시 쿠폰지급",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "성공 여부 반환"
                    )
            }
    )
    @PostMapping("/api/client/welcome/{clientId}")
    ResponseEntity<String> payWelcomeCoupon(
            @Parameter(description = "회원아이디")
            @PathVariable long clientId);
}
