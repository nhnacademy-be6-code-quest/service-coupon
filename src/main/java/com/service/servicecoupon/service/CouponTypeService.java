package com.service.servicecoupon.service;

import com.service.servicecoupon.dto.response.CouponTypeResponseDto;

import java.util.List;

public interface CouponTypeService {

    /**
     * 모든 쿠폰타입의 정보를 반환하는 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @return 모든 쿠폰타입의 정보를 반환
     */
    List<CouponTypeResponseDto> findAllCouponType();
}
