package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.dto.response.CouponTypeResponseDto;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.CouponTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CouponTypeServiceImpl implements CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;

    @Override
    public List<CouponTypeResponseDto> findAllCouponType() {
        return couponTypeRepository.findAll().stream()
            .map(this::convertToResponseDto)
            .toList();

    }

    private CouponTypeResponseDto convertToResponseDto(CouponType couponType) {
        return new CouponTypeResponseDto(couponType.getCouponTypeId(), couponType.getCouponKind());
    }
}


