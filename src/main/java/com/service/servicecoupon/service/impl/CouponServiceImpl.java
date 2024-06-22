package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;

import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements CouponService{
    @Autowired
    private final CouponRepository couponRepository;
    @Autowired
    private final CouponTypeRepository couponTypeRepository;
    @Autowired
    private final CouponPolicyRepository couponPolicyRepository;

    @Override
    public void save(CouponRequestDto couponRequest, long couponPolicyId){
        LocalDateTime now = LocalDateTime.now();

        CouponType couponType = couponTypeRepository.findById(couponRequest.couponTypeId()
        ).orElseThrow();
        CouponPolicy couponPolicy = couponPolicyRepository.findById(couponPolicyId).orElseThrow();
        Coupon coupon=new Coupon(couponRequest.clientId(),couponType,couponPolicy,couponRequest.expirationDate(),couponRequest.status());
        coupon.setIssuedDate(now);

       couponRepository.save(coupon);
    }

    @Override
    public List<CouponResponseDto> findByClientId(long clientId){
        List<CouponResponseDto> coupons = couponRepository.findByClientId(clientId);
        return coupons != null ? coupons : new ArrayList<>();
    }
}

