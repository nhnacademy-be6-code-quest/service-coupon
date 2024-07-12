package com.service.servicecoupon.client;
import com.service.servicecoupon.dto.request.CouponPaymentRewardRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
@FeignClient(name = "paymentReward", url ="http://localhost:8001")
public interface PaymentCouponClient {

        @GetMapping("/api/client/birth-coupon")
        CouponPaymentRewardRequestDto getUserPaymentValue();
    }

