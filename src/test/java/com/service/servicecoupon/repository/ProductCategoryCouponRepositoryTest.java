//package com.service.servicecoupon.repository;
//
//import com.service.servicecoupon.domain.DiscountType;
//import com.service.servicecoupon.domain.entity.CouponPolicy;
//import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import static org.springframework.test.util.ReflectionTestUtils.setField;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//class ProductCategoryCouponRepositoryTest {
//
//    @Autowired
//    private ProductCategoryCouponRepository productCategoryCouponRepository;
//
//    @Autowired
//    private CouponPolicyRepository couponPolicyRepository;
//
//    @BeforeEach
//    void setUp() {
//
//        CouponPolicy couponPolicy = new CouponPolicy("Policy description",
//            DiscountType.AMOUNTDISCOUNT, 100, 50, 500);
//        setField(couponPolicy,"couponPolicyId",1L);
//        couponPolicyRepository.save(couponPolicy);
//
//        ProductCategoryCoupon productCategoryCoupon = new ProductCategoryCoupon(1L, couponPolicy);
//
//        productCategoryCouponRepository.save(productCategoryCoupon);
//    }
//
//    @Test
//    void testFindByCategoryPolicy_CouponPolicyId() {
//        // Given
//        Long couponPolicyId = 1L;
//
//        // When
//        ProductCategoryCoupon foundCoupon = productCategoryCouponRepository.findByCategoryPolicy_CouponPolicyId(
//            couponPolicyId);
//
//        // Then
//        Assertions.assertNotNull(foundCoupon);
//        Assertions.assertEquals(couponPolicyId,
//            foundCoupon.getCategoryPolicy().getCouponPolicyId());
//    }
//}
