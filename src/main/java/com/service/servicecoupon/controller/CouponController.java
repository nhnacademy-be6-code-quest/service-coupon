package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.dto.request.CouponRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.CouponNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.CouponTypeNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

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
    List<CouponOrderResponseDto> findClientCoupon(
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
    ResponseEntity<Page<CouponMyPageCouponResponseDto>> findMyPageCoupons(
        @Parameter(description = "쿠폰을 조회하는 회원의 아이디")
        @RequestHeader HttpHeaders httpHeaders,
        @Parameter(description = "페이지")
        @RequestParam(name = "page") int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam(name = "size") int size,
        @Parameter(description = "쿠폰 상태")
        @RequestParam Status status);

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
    ResponseEntity<CouponRegisterRequestDto> saveCoupon(
        @Parameter(description = "쿠폰정책 아이디")
        @PathVariable long couponPolicyId,
        @Parameter(description = "쿠폰 정보")
        @RequestBody CouponRegisterRequestDto couponRequest);




    @Operation(
        summary = "쿠폰 상태 변경",
        description = "refund - 환불시 쿠폰 사용일,상태 변경",
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
    @GetMapping("/api/coupon/adminPage")
    ResponseEntity<Page<CouponAdminPageCouponResponseDto>> findUserCoupons (
        @Parameter(description = "페이지")
        @RequestParam int page,
        @Parameter(description = "페이지에 보여줄 개수")
        @RequestParam int size,
        @Parameter(description = "쿠폰 상태")
        @RequestParam Status status);

    @PostMapping("/api/coupon/client/reward")
    ResponseEntity<String> rewardUserCoupon (@RequestHeader HttpHeaders httpHeaders, String methodName);

    @ExceptionHandler(ClientNotFoundException.class)
    ResponseEntity<String> handleExceptionClientNotFoundException(ClientNotFoundException e);

    @ExceptionHandler(CouponNotFoundException.class)
    ResponseEntity<String> handleCouponNotFoundException(ClientNotFoundException e);

    @ExceptionHandler(CouponPolicyNotFoundException.class)
    ResponseEntity<String> handleCouponPolicyNotFoundException(
        CouponPolicyNotFoundException e);

    @ExceptionHandler(CouponTypeNotFoundException.class)
    ResponseEntity<String> handleCouponTypeNotFound(CouponTypeNotFoundException e);
}
