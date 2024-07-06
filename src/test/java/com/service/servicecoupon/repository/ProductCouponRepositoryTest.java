//package com.service.servicecoupon.repository;
//
//import com.service.servicecoupon.domain.DiscountType;
//import com.service.servicecoupon.domain.entity.CouponPolicy;
//import com.service.servicecoupon.domain.entity.ProductCoupon;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.transaction.annotation.Transactional;
//import static org.springframework.test.util.ReflectionTestUtils.setField;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@Transactional
//class ProductCouponRepositoryTest {
//
//    @Autowired
//    private ProductCouponRepository productCouponRepository;
//
//    @Autowired
//    private CouponPolicyRepository couponPolicyRepository;
//
//    @BeforeEach
//    void setUp() {
//        CouponPolicy couponPolicy = new CouponPolicy("Policy description",
//            DiscountType.PERCENTAGEDISCOUNT, 10, 0, 1000);
//        setField(couponPolicy, "couponPolicyId", 1L);
//
//        couponPolicyRepository.save(couponPolicy);
//
//        ProductCoupon productCoupon = new ProductCoupon(1L, couponPolicy);
//
//        productCouponRepository.save(productCoupon);
//    }
//
//    @Test
//    void testFindByProductPolicy_CouponPolicyId() {
//        // Given
//        Long couponPolicyId = 1L;
//
//        // When
//        ProductCoupon foundCoupon = productCouponRepository.findByProductPolicy_CouponPolicyId(couponPolicyId);
//
//        // Then
//        Assertions.assertNotNull(foundCoupon);
//        Assertions.assertEquals(couponPolicyId, foundCoupon.getProductPolicy().getCouponPolicyId());
//    }
//}