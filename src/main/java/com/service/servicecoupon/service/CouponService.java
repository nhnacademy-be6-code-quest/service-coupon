package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponService {
    private final CouponRepository couponRepository;

    public Coupon save(Coupon coupon){
        return couponRepository.save(coupon);
    }
    public Coupon findById(long id){
        return couponRepository.findById(id).orElse(null);
    }
    public List<Coupon> findAllCoupon(){
        return couponRepository.findAll();
    }
    public void delete(long id){
        couponRepository.deleteById(id);
    }
}
