package com.service.servicecoupon.repository;

import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.entity.CouponType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CouponTypeRepositoryTest {

    @Autowired
    private CouponTypeRepository couponTypeRepository;

    @BeforeEach
    void setUp() {
        CouponType couponType = new CouponType();
        setField(couponType,"couponTypeId",1L);
        setField(couponType,"couponKind",CouponKind.DISCOUNT);
        couponTypeRepository.save(couponType);
    }

    @Test
    void testFindByCouponKind() {
        // Given
        CouponKind couponKind = CouponKind.DISCOUNT;

        // When
        CouponType foundCouponType = couponTypeRepository.findByCouponKind(couponKind);

        // Then
        Assertions.assertNotNull(foundCouponType);
        Assertions.assertEquals(couponKind, foundCouponType.getCouponKind());
    }
}
