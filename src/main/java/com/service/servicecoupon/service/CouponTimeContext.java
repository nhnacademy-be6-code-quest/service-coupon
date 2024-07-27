package com.service.servicecoupon.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CouponTimeContext {
    Map<String, Integer> couponTimes = new HashMap<>();

    public CouponTimeContext() {
        couponTimes.put("summer",60);
        couponTimes.put("new",30);
        couponTimes.put("nhn",365);
        couponTimes.put("special",30);
    }
    public Integer getTime(String methodName) {
        return couponTimes.get(methodName);
    }

}
