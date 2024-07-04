package com.service.servicecoupon.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "birthdayUser", url ="http://localhost:8003")
public interface BirthDayUserClient {
    @GetMapping("/api/client/birth-coupon")
    List<Long> getThisMonthBirthClient();
}
