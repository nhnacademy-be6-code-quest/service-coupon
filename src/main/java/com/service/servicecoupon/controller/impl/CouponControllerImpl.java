package com.service.servicecoupon.controller.impl;


import com.service.servicecoupon.controller.CouponController;
import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponControllerImpl implements CouponController {
    private static final String ID_HEADER = "X-User-Id";

    private final CouponService couponService;

    @Override
    @GetMapping("/api/coupon")
    public List<CouponResponseDto> couponFind(@RequestHeader HttpHeaders httpHeaders) {
        return couponService.findByClientId(Long.parseLong(httpHeaders.getFirst(ID_HEADER)));
    }
    @Override
    @PostMapping("/api/coupon/register/{couponPolicyId}")
    public ResponseEntity<CouponRequestDto> saveCoupon(@PathVariable long couponPolicyId, @RequestBody CouponRequestDto couponRequest) {
        couponService.save(couponRequest,couponPolicyId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(couponRequest);
    }
    @Override
    @PutMapping("/api/coupon/update")
    public ResponseEntity<String> updateCoupon(long couponId){
        try {
            couponService.update(couponId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    @PostMapping("/api/client/welcome/{clientId}")
    public ResponseEntity<String> payWelcomeCoupon(@PathVariable long clientId){
        try {
            couponService.payWelcomeCoupon(clientId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PutMapping("/api/coupon/refund")
    public ResponseEntity<String> refundCoupon(long couponId){
        try {
            couponService.refundCoupon(couponId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }
}
