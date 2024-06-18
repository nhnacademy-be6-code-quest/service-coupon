package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponPolicyService {
    private final CouponPolicyRepository couponPolicyRepository;

    public CouponPolicy save(CouponPolicy couponPolicy){
        return couponPolicyRepository.save(couponPolicy);
    }
    public CouponPolicy findById(long id){
        return couponPolicyRepository.findById(id).orElse(null);
    }
    public List<CouponPolicy> findAllCouponPolicy(){
        return couponPolicyRepository.findAll();
    }
    public void deleteById( long id ) {
        couponPolicyRepository.deleteById(id);
    }
}