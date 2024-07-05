package com.service.servicecoupon.controller.impl;


import com.service.servicecoupon.controller.CouponController;
import com.service.servicecoupon.dto.request.CouponRequestDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponIssuedResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.PaymentCompletedCouponResponseDto;
import com.service.servicecoupon.dto.response.RefundCouponResponseDto;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.CouponNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.CouponTypeNotFound;
import com.service.servicecoupon.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponControllerImpl implements CouponController {

    private final CouponService couponService;

    @Override
    @GetMapping("/api/coupon")
    public List<CouponOrderResponseDto> couponFind(@RequestHeader HttpHeaders httpHeaders) {
        return couponService.findClientCoupon(httpHeaders);
    }

    @Override
    @GetMapping("/api/coupon/myPage")
    public ResponseEntity<Page<CouponMyPageCouponIssuedResponseDto>> findMyPageCoupons(
        @RequestHeader HttpHeaders httpHeaders, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(couponService.findByClientId(httpHeaders, page, size));
    }

    @Override
    @PostMapping("/api/coupon/register/{couponPolicyId}")
    public ResponseEntity<CouponRequestDto> saveCoupon(@Valid @PathVariable long couponPolicyId,
        @RequestBody CouponRequestDto couponRequest) {
        couponService.save(couponRequest, couponPolicyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(couponRequest);
    }

    @Override
    @PutMapping("/api/coupon/payment")
    public ResponseEntity<String> UseCoupon(
        @RequestBody PaymentCompletedCouponResponseDto paymentCompletedCouponResponseDto) {
        try {
            couponService.paymentCompletedCoupon(paymentCompletedCouponResponseDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @PutMapping("/api/coupon/refund")
    public ResponseEntity<String> refundCoupon(
        @RequestBody RefundCouponResponseDto refundCouponResponseDto) {
        try {
            couponService.refundCoupon(refundCouponResponseDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handleExceptionClientNotFoundException(
        ClientNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<String> handleCouponNotFoundException(ClientNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    @ExceptionHandler(CouponPolicyNotFoundException.class)
    public ResponseEntity<String> handleCouponPolicyNotFoundException(
        CouponPolicyNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @Override
    @ExceptionHandler(CouponTypeNotFound.class)
    public ResponseEntity<String> handleCouponTypeNotFound(CouponTypeNotFound e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
