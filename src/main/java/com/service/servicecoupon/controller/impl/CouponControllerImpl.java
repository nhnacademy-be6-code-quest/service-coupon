package com.service.servicecoupon.controller.impl;


import com.service.servicecoupon.controller.CouponController;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.dto.request.CouponRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.CouponNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.CouponTypeNotFoundException;
import com.service.servicecoupon.service.CouponService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CouponControllerImpl implements CouponController {

    private final CouponService couponService;

    @Override
    @GetMapping("/api/coupon")
    public List<CouponOrderResponseDto> findClientCoupon(@RequestHeader HttpHeaders httpHeaders) {
        return couponService.findClientCoupon(httpHeaders);
    }

    @Override
    @GetMapping("/api/coupon/myPage")
    public ResponseEntity<Page<CouponMyPageCouponResponseDto>> findMyPageCoupons(
        @RequestHeader HttpHeaders httpHeaders, @RequestParam int page, @RequestParam int size, @RequestParam Status status) {
        return ResponseEntity.ok(couponService.findByClientId(httpHeaders, page, size, status));
    }

    @Override
    @PostMapping("/api/coupon/register/{couponPolicyId}")
    public ResponseEntity<CouponRegisterRequestDto> saveCoupon(@Valid @PathVariable long couponPolicyId,
        @RequestBody CouponRegisterRequestDto couponRequest) {
        couponService.save(couponRequest, couponPolicyId);
        return ResponseEntity.status(HttpStatus.CREATED).body(couponRequest);
    }



    @Override
    @GetMapping("/api/coupon/adminPage")
    public ResponseEntity<Page<CouponAdminPageCouponResponseDto>> findUserCoupons (@RequestParam int page, @RequestParam int size, @RequestParam Status status){
        return ResponseEntity.ok(couponService.findByAllCoupon(page,size,status));
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
    @ExceptionHandler(CouponTypeNotFoundException.class)
    public ResponseEntity<String> handleCouponTypeNotFound(CouponTypeNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
