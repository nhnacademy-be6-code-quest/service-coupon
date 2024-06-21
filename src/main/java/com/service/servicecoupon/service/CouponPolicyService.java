package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.request.CouponPolicyRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.repository.CouponPolicyRepository;

import static java.util.Arrays.stream;


@RequiredArgsConstructor
@Service
public class CouponPolicyService {
    private final CouponPolicyRepository couponPolicyRepository;

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
    public CouponPolicy findById(long couponPolicyId){
        return couponPolicyRepository.findById(couponPolicyId).orElse(null);
    }
    public Page<CouponPolicyResponseDto> getPolicies(Pageable pageable) {
        Page<CouponPolicy> coupons = couponPolicyRepository.findAll(pageable);
        return coupons.map(CouponPolicyResponseDto::new);
    }
    private CouponPolicyResponseDto convertToDto(CouponPolicy couponPolicy){
        return CouponPolicyResponseDto.builder()
                .couponPolicyId(couponPolicy.getCouponPolicyId())
                .productId(couponPolicy.getProductId())
                .productCategoryId(couponPolicy.getProductCategoryId())
                .discountType(couponPolicy.getDiscountType())
                .couponPolicyDescription(couponPolicy.getCouponPolicyDescription())
                .discountValue(couponPolicy.getDiscountValue())
                .minPurchaseAmount(couponPolicy.getMinPurchaseAmount())
                .maxDiscountAmount(couponPolicy.getMaxDiscountAmount())
                .build();
    }
    public CouponPolicyResponseDto getPolicy(long couponPolicyId){
        CouponPolicy couponPolicy = findById(couponPolicyId);
        return new CouponPolicyResponseDto(couponPolicy);
    }

//    public void deleteById( long id ) {
//        couponPolicyRepository.deleteById(id);
//    }
}
