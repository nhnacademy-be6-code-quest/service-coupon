package com.service.servicecoupon.service;

public interface SchedulerService {

    /**
     * 매월 1일 0시에 해당월에 생일자인 회원아이디를 받아 생일 쿠폰을 지급
     *
     * @author jjeonmin
     */
    void birthCoupon();

    /**
     * 매일 12시 쿠폰의 만료시간이 현재일보다 작으면 상태를 변경
     *
     * @author jjeonmin
     */
    void run();
}
