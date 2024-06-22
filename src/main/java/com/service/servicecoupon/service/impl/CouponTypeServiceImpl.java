package com.service.servicecoupon.service.impl;

import java.util.List;

import com.service.servicecoupon.service.CouponTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.repository.CouponTypeRepository;


@RequiredArgsConstructor
@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;

    @Override
    public List<CouponType> findAllCouponType(){
        return couponTypeRepository.findAll();

    }
}


