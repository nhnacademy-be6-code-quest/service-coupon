package com.service.servicecoupon.service;

import com.service.servicecoupon.client.BirthDayUserClient;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final CouponRepository couponRepository;
    private final BirthDayUserClient birthDayUserClient;
    private final CouponTypeRepository couponTypeRepository;
    private final CouponPolicyRepository couponPolicyRepository;
    //private static final int CHUNK_SIZE = 1000;
    //TODO 청크사이즈 제한 고민




    @Scheduled(cron = " 0 0 0 1 * * ")
    public void birthCoupon(){
        CouponType couponType = couponTypeRepository.findByCouponKind(CouponKind.BIRTHDAY);
        CouponPolicy couponPolicy = CouponPolicy.builder()
                .couponPolicyDescription("생일 축하 쿠폰")
                .discountType(DiscountType.AMOUNTDISCOUNT)
                .minPurchaseAmount(0)
                .maxDiscountAmount(30000)
                .discountValue(30000)
                .build();
        couponPolicyRepository.save(couponPolicy);



                // 현재 날짜 구하기
                LocalDate currentDate = LocalDate.now();

                // 현재 달의 YearMonth 객체 생성
                YearMonth currentYearMonth = YearMonth.from(currentDate);

                LocalDate lastDayOfMonth = currentYearMonth.atEndOfMonth(); // 마지막 날



        List<Long> users = birthDayUserClient.getThisMonthBirthClient();
        users.forEach((userId ->{
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
    //now() 하루에 12시에 돌면서 같으면 만료일  사용불가로
    @Scheduled(cron = " 0 0 0 * * * ")
    public void run(){
        LocalDate now = LocalDate.now();
        List<Coupon> coupons = couponRepository.findByExpirationDateBefore(now);
        coupons.forEach((row ->{
            row.setStatus(Status.UNAVAILABLE);
        }));

            couponRepository.saveAll(coupons);
        }
    }

