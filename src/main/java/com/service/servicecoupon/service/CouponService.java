package com.service.servicecoupon.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.domain.request.CouponRequest;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.repository.CouponRepository;


@RequiredArgsConstructor
@Service
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponTypeRepository couponTypeRepository;
    private final CouponPolicyRepository couponPolicyRepository;

    public Coupon save(CouponRequest couponRequest){
        LocalDateTime now = LocalDateTime.now();

        CouponType couponType = couponRequest.couponTypeId() == null ? null : couponTypeRepository.findById(couponRequest.couponTypeId()).orElseThrow();
        CouponPolicy couponPolicy =  couponRequest.couponPolicyId() == null ? null : couponPolicyRepository.findById(couponRequest.couponPolicyId()).orElseThrow();
        Coupon coupon=new Coupon(couponRequest.clientId(),couponType,couponPolicy,couponRequest.expirationDate(),couponRequest.status());
        coupon.setIssuedDate(now);

        return couponRepository.save(coupon);
    }

    public Coupon findCoupon(long couponId){
        return couponRepository.findById(couponId).orElse(null);
    }
    public List<Coupon> findByClientId(long clientId){
        List<Coupon> coupons = couponRepository.findByClientId(clientId);
        return coupons != null ? coupons : new ArrayList<>();
    }
    public Coupon updateCoupon(long couponId){
        LocalDateTime now = LocalDateTime.now();
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        coupon.setUsedDate(now);
        coupon.setStatus(Status.USED);
        return couponRepository.save(coupon);
    }
//    public List<Coupon> findAllCoupon(){
//        return couponRepository.findAll();
//    }
//    public void delete(long id){
//        couponRepository.deleteById(id);
//    }
}
