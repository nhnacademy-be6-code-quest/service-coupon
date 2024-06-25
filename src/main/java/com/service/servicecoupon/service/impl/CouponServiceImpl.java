package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;

import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.domain.response.CouponTypeResponseDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.CouponService;
import com.service.servicecoupon.service.CouponTypeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
        CouponType couponType = couponTypeRepository.findById(couponRequest.couponTypeId()
        ).orElseThrow();
        CouponPolicy couponPolicy = couponPolicyRepository.findById(couponPolicyId).orElseThrow();
        Coupon coupon=new Coupon(couponRequest.clientId(),couponType,couponPolicy,couponRequest.expirationDate(),couponRequest.status());

       couponRepository.save(coupon);
    }

    @Override
    public List<CouponResponseDto> findByClientId(long clientId){
        List<Coupon> coupons = couponRepository.findByClientId(clientId);

        return coupons.stream()
                .map(coupon ->  CouponResponseDto.builder()
                        .couponId(coupon.getCouponId())
                        .couponType(new CouponTypeResponseDto(coupon.getCouponType().getCouponTypeId(),coupon.getCouponType().getCouponKind()))
                        .couponPolicy(new CouponPolicyResponseDto(coupon.getCouponPolicy()))
                        .issuedDate(coupon.getIssuedDate())
                        .clientId(coupon.getClientId())
                        .expirationDate(coupon.getExpirationDate())
                        .usedDate(coupon.getUsedDate())
                        .status(coupon.getStatus())
                        .build())
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void update(long couponId){
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        coupon.setUsedDate(LocalDateTime.now());
        coupon.setStatus(Status.USED);
        couponRepository.save(coupon);
    }

    @Override
    public void payWelcomeCoupon(long clientId){
        CouponPolicy couponPolicy = couponPolicyRepository.findById(1L).orElse(null);
        CouponType couponType = couponTypeRepository.findById(1L).orElse(null);
        Coupon coupon = new Coupon(clientId, couponType, couponPolicy,LocalDateTime.now().plusDays(30), Status.AVAILABLE);
        couponRepository.save(coupon);
    }
}

