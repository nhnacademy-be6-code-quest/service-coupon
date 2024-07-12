package com.service.servicecoupon.client;
import com.service.servicecoupon.dto.request.CouponPaymentRewardRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentReward", url ="http://localhost:8001")
public interface PaymentCouponClient {

        @GetMapping("/api/coupon/payment/reward")
        CouponPaymentRewardRequestDto getUserPaymentValue(@RequestBody CouponPaymentRewardRequestDto couponPaymentRewardRequestDto);
    }

