package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.*;
import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.*;
import com.service.servicecoupon.repository.*;
import com.service.servicecoupon.service.CouponService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;
    private final CouponTypeRepository couponTypeRepository;
    private final CouponPolicyRepository couponPolicyRepository;
    private final ProductCouponRepository productCouponRepository;
    private final ProductCategoryCouponRepository productCategoryCouponRepository;

    @Override
    public void save(CouponRequestDto couponRequest, long couponPolicyId){
        CouponType couponType = couponTypeRepository.findById(couponRequest.couponTypeId()
        ).orElseThrow();
        CouponPolicy couponPolicy = couponPolicyRepository.findById(couponPolicyId).orElseThrow();
        List<Long> clientIds = couponRequest.clientId();
        for (Long clientId : clientIds){
            Coupon coupon=new Coupon(clientId,couponType,couponPolicy,couponRequest.expirationDate(),couponRequest.status());
            couponRepository.save(coupon);
        }
    }

    @Override
    public List<CouponResponseDto> findByClientId(long clientId){
        List<Coupon> coupons = couponRepository.findByClientId(clientId);


        return coupons.stream()
                .map(coupon -> {
                    CouponResponseDto couponResponseDto = CouponResponseDto.builder()
                            .couponId(coupon.getCouponId())
                            .couponType(new CouponTypeResponseDto(coupon.getCouponType().getCouponTypeId(), coupon.getCouponType().getCouponKind()))
                            .couponPolicy(new CouponPolicyResponseDto(coupon.getCouponPolicy()))
                            .issuedDate(coupon.getIssuedDate())
                            .clientId(coupon.getClientId())
                            .expirationDate(coupon.getExpirationDate())
                            .usedDate(coupon.getUsedDate())
                            .status(coupon.getStatus())
                            .build();


                    // 상품 쿠폰 정보 설정 (예시)
                    ProductCoupon productCoupon = productCouponRepository.findByProductPolicy_CouponPolicyId(coupon.getCouponPolicy().getCouponPolicyId());
                    if (productCoupon != null) {
                        ProductCouponResponseDto productCouponResponseDto = new ProductCouponResponseDto(productCoupon.getProductId());
                        couponResponseDto.getCouponPolicy().setProductCouponResponseDto(productCouponResponseDto);
                    }

                    ProductCategoryCoupon productCategoryCoupon = productCategoryCouponRepository.findByCategoryPolicy_CouponPolicyId(coupon.getCouponPolicy().getCouponPolicyId());

                    if (productCategoryCoupon != null) {
                        ProductCategoryCouponResponseDto productCategoryCouponResponseDto = new ProductCategoryCouponResponseDto(productCategoryCoupon.getProductCategoryId());
                        couponResponseDto.getCouponPolicy().setProductCategoryCouponResponseDto(productCategoryCouponResponseDto);
                    }
                    return couponResponseDto;
                })
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

    @Override
    public void refundCoupon(long couponId){
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        coupon.setUsedDate(null);
        coupon.setStatus(Status.AVAILABLE);

        couponRepository.save(coupon);
    }
}

