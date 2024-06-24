package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.domain.response.CouponTypeResponseDto;

import java.util.List;

public interface CouponTypeService {

    List<CouponTypeResponseDto> findAllCouponType();
}
