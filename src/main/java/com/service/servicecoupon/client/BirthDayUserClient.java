package com.service.servicecoupon.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "birthdayUser", url ="http://localhost:8001")
public interface BirthDayUserClient {
    @GetMapping("/api/client/birth-coupon")
    List<Long> getThisMonthBirthClient();
}
