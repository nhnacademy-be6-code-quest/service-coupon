package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.request.CouponPolicyRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CouponPolicyServiceImpl implements CouponPolicyService {
    private final CouponPolicyRepository couponPolicyRepository;

    @Override
    public void save(CouponPolicyRequestDto couponPolicyRequestDto){
        CouponPolicy couponPolicy=new CouponPolicy();
        couponPolicy.setProductId(couponPolicyRequestDto.productId());
        couponPolicy.setProductCategoryId(couponPolicyRequestDto.productCategoryId());
        couponPolicy.setCouponPolicyDescription(couponPolicyRequestDto.couponPolicyDescription());
        couponPolicy.setDiscountType(couponPolicyRequestDto.discountType());
        couponPolicy.setDiscountValue(couponPolicyRequestDto.discountValue());
        couponPolicy.setMinPurchaseAmount(couponPolicyRequestDto.minPurchaseAmount());
        couponPolicy.setMaxDiscountAmount(couponPolicyRequestDto.maxDiscountAmount());
        couponPolicyRepository.save(couponPolicy);
    }
    @Override
    public CouponPolicy findById(long couponPolicyId){
        return couponPolicyRepository.findById(couponPolicyId).orElse(null);
    }
    @Override
    public Page<CouponPolicyResponseDto> getPolicies(Pageable pageable) {
        Page<CouponPolicy> coupons = couponPolicyRepository.findAll(pageable);
        return coupons.map(CouponPolicyResponseDto::new);
    }

    public CouponPolicyResponseDto getPolicy(long couponPolicyId){
        CouponPolicy couponPolicy = findById(couponPolicyId);
        return new CouponPolicyResponseDto(couponPolicy);
    }
}
