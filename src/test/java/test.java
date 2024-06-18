//package com.service.service_coupon;
//
//import domain.entity.Coupon;
//import domain.entity.Status;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.jdbc.Sql;
//import repository.CouponRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//@SpringBootTest
//class test {
//    @Mock
//    private CouponRepository couponRepository;
//
//    @InjectMocks
//    private CouponService couponService;
//
//    @Sql("classpath:user-test.sql")
//    @Test
//    void userNotFoundTest() {
//        // given
//        int id = 1;
//
//        // when
//        Coupon user = couponService.findById(id);
//
//        // then
//        assertThat(user).isNull();
//    }
//
//    @Test
//    void createUserTest() {
//        // given
//        int id = 6;
//        Coupon coupon = new Coupon(id, Status.USED);
//        couponService.save(coupon);
//
//        // when
//        Coupon found = couponService.findById(id);
//
//        // then
//        assertThat(found).isNotNull();
//        assertThat(found.getCoupon_id()).isEqualTo(id);
//    }
//}
