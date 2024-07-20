package com.service.servicecoupon.service;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.dto.request.CouponRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.PaymentCompletedCouponResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CouponService {

    /**
     * 쿠폰정책 아이디와 쿠폰정보를 받아 회원에게 쿠폰 지급을 위한 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param couponRequest 쿠폰을 지급을위한 정보 Dto
     * @param couponPolicyId 쿠폰 정책 아이디
     */
    void save(CouponRegisterRequestDto couponRequest, long couponPolicyId);

    /**
     * 주문시 해당하는 회원이 보유한 쿠폰 리스트를 반환
     *
     * @author jjeonmin
     * @param httpHeaders 회원 아이디
     * @return 회원아이디에 해당하는 쿠폰 정보들을 반환
     */
    List<CouponOrderResponseDto> findClientCoupon(HttpHeaders httpHeaders);

    /**
     * 마이페이지에서 페이지와, 한 페이지의 사이즈를 인자로 받아 해당 유저의 쿠폰 내역 정보를 반환하는 함수 (권한 필요)
     *
     * @author jjeonmin
     * @param httpHeaders 회원 아이디
     * @param page 요구하는 페이지의 값
     * @param size 한페이지 내의 보여질 정보의 갯수
     * @param status 쿠폰 상태
     * @return 해당하는 페이지의 유저 쿠폰 내역 페이지를 반환
     */
    Page<CouponMyPageCouponResponseDto> findByClientId(HttpHeaders httpHeaders,
        int page,
        int size, Status status);

    /**
     * 관리자페이지에서 페이지와, 한 페이지의 사이즈를 인자로 받아 모든 유저의 쿠폰 내역 정보를 반환하는 함수 (관리자 권한 필요)
     *
     * @author jjeonmin
     * @param page 요구하는 페이지의 값
     * @param size 한페이지 내의 보여질 정보의 갯수
     * @param status 쿠폰 상태
     * @return 해당하는 페이지의 모든 유저의 쿠폰 내역 페이지를 반환
     */
    Page<CouponAdminPageCouponResponseDto> findByAllCoupon(int page, int size, Status status);

    /**
     * 결제시 사용한 쿠폰의 상태를 사용완료로 변경하는 함수
     *
     * @author jjeonmin
     * @param message 쿠폰정보 Dto
     */
    void paymentCompletedCoupon(
        String message);

    /**
     * 회원가입시 회원아이디를 받아 쿠폰지급을 위한 메시지큐
     *
     * @author jjeonmin
     * @param message 회원가입 유저 정보 Dto
     */
    void payWelcomeCoupon(String message);


    /**
     * 환불시 쿠폰을 사용가능 상태로 변경하는 함수
     *
     * @author jjeonmin
     * @param message 결제시 사용한 쿠폰정보 Dto
     */
    void refundCoupon(String message);


}
