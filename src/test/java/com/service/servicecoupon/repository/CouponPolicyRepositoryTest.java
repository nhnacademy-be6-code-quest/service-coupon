package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest // JPA 관련 테스트를 위한 설정 활성화
class CouponPolicyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager; // 테스트용 엔티티 매니저

    @Autowired
    private CouponPolicyRepository couponPolicyRepository; // 테스트할 리포지토리 인스턴스

    @Test
    void testFindTopByCouponPolicyDescriptionContainingOrderByIdDesc() {
        // Given
        CouponPolicy couponPolicy1 = new CouponPolicy("생일 쿠폰", DiscountType.AMOUNTDISCOUNT, 10000, 50000, 10000);
        CouponPolicy couponPolicy2 = new CouponPolicy("할인 쿠폰", DiscountType.PERCENTAGEDISCOUNT, 0, 0, 0);
        entityManager.persist(couponPolicy1);
        entityManager.persist(couponPolicy2);
        entityManager.flush();

        // When
        CouponPolicy foundPolicy = couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc("쿠폰");

        // Then
        assertNotNull(foundPolicy);
        assertEquals("할인 쿠폰", foundPolicy.getCouponPolicyDescription()); // 가장 최근에 추가된 쿠폰 정책 검색
    }
}
