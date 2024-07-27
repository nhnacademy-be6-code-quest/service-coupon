package com.service.servicecoupon.service.impl;

import com.service.servicecoupon.client.BirthDayUserClient;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.service.SchedulerService;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SchedulerServiceImpl implements SchedulerService {

    private final CouponRepository couponRepository;
    private final BirthDayUserClient birthDayUserClient;
    private final CouponTypeRepository couponTypeRepository;
    private final CouponPolicyRepository couponPolicyRepository;



    @Scheduled(cron = "0 0 15 * * *") // 매 5초마다 실행

    public void birthCoupon() {
        CouponType couponType = couponTypeRepository.findByCouponKind(CouponKind.BIRTHDAY);
        CouponPolicy couponPolicy = couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc(
            "생일");
        if (couponPolicy == null) {
            throw new CouponPolicyNotFoundException("쿠폰 정책을 찾을수 없습니다.");
        }

        LocalDate currentDate = LocalDate.now();                // 현재 날짜 구하기

        YearMonth currentYearMonth = YearMonth.from(currentDate);   // 현재 달의 YearMonth 객체 생성

        LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth(); // 마지막 날

        List<Long> users = birthDayUserClient.getThisMonthBirthClient();
        users.forEach((userId -> {
            Coupon coupon = Coupon.builder()
                .clientId(userId)
                .couponPolicy(couponPolicy)
                .couponType(couponType)
                .status(Status.AVAILABLE)
                .expirationDate(lastDayOfMonth)
                .build();
            couponRepository.save(coupon);
        }));

    }
}

