package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.repository.ProductCouponRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductCouponServiceImpl {

    private final ProductCouponRepository productCouponRepository;

    public void save(ProductCoupon productCoupon) {
        productCouponRepository.save(productCoupon);
    }
}
