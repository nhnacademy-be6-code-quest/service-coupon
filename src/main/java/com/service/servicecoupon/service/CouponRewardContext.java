package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
@Component
public class CouponRewardContext {

    Map<String, CouponPolicy> couponPolicyMap = new HashMap<>();


    public CouponRewardContext(CouponPolicyRepository couponPolicyRepository) {
        couponPolicyMap.put("summer", couponPolicyRepository.findById(3L).orElse(null));
        couponPolicyMap.put("new", couponPolicyRepository.findById(4L).orElse(null));
        couponPolicyMap.put("nhn", couponPolicyRepository.findById(5L).orElse(null));
        couponPolicyMap.put("special", couponPolicyRepository.findById(6L).orElse(null));
    }

    public CouponPolicy getPolicy(String methodName) {
        return couponPolicyMap.get(methodName);
    }

}
