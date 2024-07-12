package com.service.servicecoupon.service;

import com.service.servicecoupon.dto.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.dto.response.CouponProvideTypeResponseDto;
import org.springframework.data.domain.Page;

public interface CouponPolicyService {

    /**
     * 쿠폰정책을 저장하기위한 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param couponPolicyRegisterRequestDt 쿠폰정책 저장정보 Dto
     */
    void save(CouponPolicyRegisterRequestDto couponPolicyRegisterRequestDt);


    /**
     * 페이지와, 한 페이지의 사이즈를 인자로 받아 모든 쿠폰 정책을 반환 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param page 요구하는 페이지의 값
     * @param size 한페이지 내의 보여질 정보의 갯수
     * @return 모든 포인트 정책 정보 페이지를 반환
     */
    Page<CouponPolicyListResponseDto> getPolicies(int page, int size);

    /**
     * 쿠폰정책아이디를 인자로 받아 해당쿠폰의 적용되는 이름을 반환 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param couponPolicyId 쿠폰정책 아이디
     * @return 해당 쿠폰정책의 적용되는 이름을 반환
     */
    CouponProvideTypeResponseDto findType(long couponPolicyId);
}
