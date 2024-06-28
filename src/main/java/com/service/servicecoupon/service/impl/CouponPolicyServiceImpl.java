package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.domain.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.domain.response.CouponPolicyResponseDto;
import com.service.servicecoupon.domain.response.ProductCategoryCouponResponseDto;
import com.service.servicecoupon.domain.response.ProductCouponResponseDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.ProductCategoryCouponRepository;
import com.service.servicecoupon.repository.ProductCouponRepository;
import com.service.servicecoupon.service.CouponPolicyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CouponPolicyServiceImpl implements CouponPolicyService {
    private final CouponPolicyRepository couponPolicyRepository;
    private final ProductCouponRepository productCouponRepository;
    private final ProductCategoryCouponRepository productCategoryCouponRepository;

    //@Override
    @Transactional
    public void save(CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDto){
        
        long id = couponPolicyRegisterRequestDto.id();
        String typeName = couponPolicyRegisterRequestDto.typeName();
        
        CouponPolicy couponPolicy=new CouponPolicy();
        couponPolicy.setCouponPolicyDescription(couponPolicyRegisterRequestDto.couponPolicyDescription());
        couponPolicy.setDiscountType(couponPolicyRegisterRequestDto.discountType());
        couponPolicy.setDiscountValue(couponPolicyRegisterRequestDto.discountValue());
        couponPolicy.setMinPurchaseAmount(couponPolicyRegisterRequestDto.minPurchaseAmount());
        couponPolicy.setMaxDiscountAmount(couponPolicyRegisterRequestDto.maxDiscountAmount());

        if (typeName != null) {
            if (typeName.equals("상품")) {
                ProductCoupon productCoupon = new ProductCoupon(id, couponPolicy);
                productCouponRepository.save(productCoupon);
            } else if (typeName.equals("카테고리")) {
                ProductCategoryCoupon productCategoryCoupon = new ProductCategoryCoupon(id, couponPolicy);
                productCategoryCouponRepository.save(productCategoryCoupon);
            }
            //TODO NULL 처리는 어떻게 할것인가?
        }
        couponPolicyRepository.save(couponPolicy);
    }
    //@Override
    public CouponPolicy findById(long couponPolicyId){
        return couponPolicyRepository.findById(couponPolicyId).orElse(null);
    }
    //@Override
    public Page<CouponPolicyResponseDto> getPolicies(Pageable pageable) {
        Page<CouponPolicy> coupons = couponPolicyRepository.findAll(pageable);
        return coupons.map(couponPolicy -> {
            CouponPolicyResponseDto dto = new CouponPolicyResponseDto(couponPolicy);


            ProductCoupon productCoupon = productCouponRepository.findByProductPolicy_CouponPolicyId(couponPolicy.getCouponPolicyId());
            if (productCoupon != null) {
                ProductCouponResponseDto productCouponResponseDto = new ProductCouponResponseDto(productCoupon.getProductId());
                dto.setProductCouponResponseDto(productCouponResponseDto);
            }
            ProductCategoryCoupon productCategoryCoupon = productCategoryCouponRepository.findByCategoryPolicy_CouponPolicyId(couponPolicy.getCouponPolicyId());
            if (productCategoryCoupon != null) {
                ProductCategoryCouponResponseDto productCategoryCouponResponseDto = new ProductCategoryCouponResponseDto(productCategoryCoupon.getProductCategoryId());
                dto.setProductCategoryCouponResponseDto(productCategoryCouponResponseDto);
            }

            return dto;
        });
    }

    public CouponPolicyResponseDto getPolicy(long couponPolicyId){
        CouponPolicy couponPolicy = findById(couponPolicyId);
        return new CouponPolicyResponseDto(couponPolicy);
    }
}
