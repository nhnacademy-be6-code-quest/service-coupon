package com.service.servicecoupon.controller;

import com.service.servicecoupon.dto.request.CouponRequestDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponIssuedResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.PaymentCompletedCouponResponseDto;
import com.service.servicecoupon.dto.response.RefundCouponResponseDto;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.CouponNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.CouponTypeNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Coupon", description = "쿠폰 관련 API")
public interface CouponController {


    @Operation(
        summary = "쿠폰 조회",
        description = "Order - 사용자 쿠폰조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "쿠폰 정보 반환"
            )
        }
    )
    @GetMapping("/api/coupon")
    List<CouponOrderResponseDto> couponFind(
        @Parameter(description = "쿠폰을 조회하는 회원의 아이디")
        @RequestHeader HttpHeaders httpHeaders);

    @Operation(
        summary = "쿠폰 조회",
        description = "MyPage - 사용자 쿠폰조회",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "쿠폰 정보 반환"
            )
        }
    )
    @GetMapping("/api/coupon/myPage")
    ResponseEntity<Page<CouponMyPageCouponIssuedResponseDto>> findMyPageCoupons(
        @Parameter(description = "쿠폰을 조회하는 회원의 아이디")
        @RequestHeader HttpHeaders httpHeaders,
        @RequestParam(name = "page") int page,
        @RequestParam(name = "size") int size);

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
            ),
            @ApiResponse(
                responseCode = "400",
                description = "실패 여부 반환"
            )
        }
    )
    @PutMapping("/api/coupon/payment")
    ResponseEntity<String> UseCoupon(
        @Parameter(description = "쿠폰 아이디")
        PaymentCompletedCouponResponseDto paymentCompletedCouponResponseDto);

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
    ResponseEntity<String> refundCoupon(
        @Parameter(description = "쿠폰아이디")
        RefundCouponResponseDto refundCouponResponseDto);


    @ExceptionHandler(ClientNotFoundException.class)
    ResponseEntity<String> handleExceptionClientNotFoundException(ClientNotFoundException e);

    @ExceptionHandler(CouponNotFoundException.class)
    ResponseEntity<String> handleCouponNotFoundException(ClientNotFoundException e);

    @ExceptionHandler(CouponPolicyNotFoundException.class)
    ResponseEntity<String> handleCouponPolicyNotFoundException(
        CouponPolicyNotFoundException e);

    @ExceptionHandler(CouponTypeNotFound.class)
    ResponseEntity<String> handleCouponTypeNotFound(CouponTypeNotFound e);
}
