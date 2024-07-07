package com.service.servicecoupon.service;

import com.service.servicecoupon.dto.response.CouponTypeResponseDto;

import java.util.List;

public interface CouponTypeService {

    List<CouponTypeResponseDto> findAllCouponType();
}
