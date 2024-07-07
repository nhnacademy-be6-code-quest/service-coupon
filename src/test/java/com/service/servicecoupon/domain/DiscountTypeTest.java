package com.service.servicecoupon.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountTypeTest {

    @Test
    void testDiscountTypeEnumValues() {
        // Test values initialization
        assertEquals(0, DiscountType.AMOUNTDISCOUNT.getCode());
        assertEquals("금액 할인", DiscountType.AMOUNTDISCOUNT.getValue());

        assertEquals(1, DiscountType.PERCENTAGEDISCOUNT.getCode());
        assertEquals("퍼센트 할인", DiscountType.PERCENTAGEDISCOUNT.getValue());
    }

    @Test
    void testFromCode() {
        assertEquals(DiscountType.AMOUNTDISCOUNT, DiscountType.fromCode(0));
        assertEquals(DiscountType.PERCENTAGEDISCOUNT, DiscountType.fromCode(1));

        // Test invalid code
        assertThrows(IllegalArgumentException.class, () -> DiscountType.fromCode(2));
    }

    @Test
    void testFromValue() {
        assertEquals(DiscountType.AMOUNTDISCOUNT, DiscountType.fromValue("금액 할인"));
        assertEquals(DiscountType.PERCENTAGEDISCOUNT, DiscountType.fromValue("퍼센트 할인"));

        // Test invalid value
        assertThrows(IllegalArgumentException.class, () -> DiscountType.fromValue("테스트"));
    }
}


