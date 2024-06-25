package com.service.servicecoupon.controller;


import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "coupon API", description = "coupon API 입니다.")
public class CouponControllerImpl {

    private final CouponService couponService;

    @Operation(summary = "사진없는 리뷰생성", description = "사진없는 리뷰를 생성합니다.")

    @GetMapping("/api/coupon/{clientId}")
    public List<CouponResponseDto> couponFind(@PathVariable long clientId) {
        return couponService.findByClientId(clientId);
    }

    @PostMapping("/api/coupon/register/{couponPolicyId}")
    public ResponseEntity<CouponRequestDto> saveCoupon(@PathVariable long couponPolicyId, @RequestBody CouponRequestDto couponRequest) {
        couponService.save(couponRequest,couponPolicyId);
        return  ResponseEntity.status(HttpStatus.CREATED).body(couponRequest);
    }

    @PutMapping("/api/coupon/update")
    public ResponseEntity<CouponResponseDto> updateCoupon(long couponId){ //pathvariable or just id
        couponService.update(couponId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/api/client/welcome/{clientId}")
    public ResponseEntity<String> payWelcomeCoupon(@PathVariable long clientId){
        try {
            couponService.payWelcomeCoupon(clientId);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    //@PutMapping("/api/coupon/refund")
    //public ResponseEntity<CouponResponseDto> refundCoupon(long couponId){
        //

    //}


}
