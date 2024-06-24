package com.service.servicecoupon.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.service.servicecoupon.domain.response.CouponTypeResponseDto;
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
    public List<CouponTypeResponseDto> findAllCouponType(){
        return couponTypeRepository.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());

    }
    private CouponTypeResponseDto convertToResponseDto(CouponType couponType) {
        return new CouponTypeResponseDto(couponType.getCouponTypeId(), couponType.getCouponKind());
    }
}


