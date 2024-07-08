package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.dto.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.dto.response.CouponProvideTypeResponseDto;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.ProductCategoryCouponRepository;
import com.service.servicecoupon.repository.ProductCouponRepository;
import com.service.servicecoupon.service.CouponPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class CouponPolicyServiceImpl implements CouponPolicyService {

    private final CouponPolicyRepository couponPolicyRepository;
    private final ProductCouponRepository productCouponRepository;
    private final ProductCategoryCouponRepository productCategoryCouponRepository;

    @Override
    public void save(CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDto) {

        long id = couponPolicyRegisterRequestDto.id();
        String typeName = couponPolicyRegisterRequestDto.typeName();

        CouponPolicy couponPolicy = CouponPolicy.builder()
            .couponPolicyDescription(couponPolicyRegisterRequestDto.couponPolicyDescription())
            .discountType(couponPolicyRegisterRequestDto.discountType())
            .discountValue(couponPolicyRegisterRequestDto.discountValue())
            .minPurchaseAmount(couponPolicyRegisterRequestDto.minPurchaseAmount())
            .maxDiscountAmount(couponPolicyRegisterRequestDto.maxDiscountAmount())
            .build();
        couponPolicyRepository.save(couponPolicy);
        if (typeName != null) {
            if (typeName.equals("상품")) {
                ProductCoupon productCoupon = new ProductCoupon(id, couponPolicy);
                productCouponRepository.save(productCoupon);

            }
            if (typeName.equals("카테고리")) {
                ProductCategoryCoupon productCategoryCoupon = new ProductCategoryCoupon(id,
                    couponPolicy);
                productCategoryCouponRepository.save(productCategoryCoupon);
            }

        }

    }

    @Transactional(readOnly = true)
    @Override
    public Page<CouponPolicyListResponseDto> getPolicies(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size,
            Sort.by("couponPolicyId").descending());

        Page<CouponPolicy> couponPolicies = couponPolicyRepository.findAll(pageRequest);
        return couponPolicies.map(couponPolicy -> {
            CouponPolicyListResponseDto dto = new CouponPolicyListResponseDto();
            dto.setCouponPolicyId(couponPolicy.getCouponPolicyId());
            dto.setCouponPolicyDescription(couponPolicy.getCouponPolicyDescription());
            dto.setDiscountType(couponPolicy.getDiscountType().getValue());
            dto.setDiscountValue(couponPolicy.getDiscountValue());
            return dto;
        });
    }

    public CouponProvideTypeResponseDto findType(long couponPolicyId){
        ProductCoupon product = productCouponRepository.findByProductPolicy_CouponPolicyId(couponPolicyId);
        ProductCategoryCoupon categoryCoupon = productCategoryCouponRepository.findByCategoryPolicy_CouponPolicyId(couponPolicyId);
        CouponProvideTypeResponseDto dto = new CouponProvideTypeResponseDto();
        if (product != null) {
            dto.setName("상품");
        } else if (categoryCoupon != null) {
            dto.setName("카테고리");
        }
        else{
            dto.setName("전체");
        }
        return dto;
    }


}
