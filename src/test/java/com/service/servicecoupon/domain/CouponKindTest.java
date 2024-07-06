package com.service.servicecoupon.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponKindTest {

    @Test
    void testCouponKindEnumValues() {
        // Test values initialization
        assertEquals(0, CouponKind.WELCOME.getCode());
        assertEquals("회원가입", CouponKind.WELCOME.getValue());

        assertEquals(1, CouponKind.BIRTHDAY.getCode());
        assertEquals("생일", CouponKind.BIRTHDAY.getValue());

        assertEquals(2, CouponKind.BOOK.getCode());
        assertEquals("상품", CouponKind.BOOK.getValue());

        assertEquals(3, CouponKind.CATEGORY.getCode());
        assertEquals("상품 카테고리", CouponKind.CATEGORY.getValue());

        assertEquals(4, CouponKind.DISCOUNT.getCode());
        assertEquals("금액 할인", CouponKind.DISCOUNT.getValue());
    }

    @Test
    void testFromCode() {
        assertEquals(CouponKind.WELCOME, CouponKind.fromCode(0));
        assertEquals(CouponKind.BIRTHDAY, CouponKind.fromCode(1));
        assertEquals(CouponKind.BOOK, CouponKind.fromCode(2));
        assertEquals(CouponKind.CATEGORY, CouponKind.fromCode(3));
        assertEquals(CouponKind.DISCOUNT, CouponKind.fromCode(4));

        assertThrows(IllegalArgumentException.class, () -> CouponKind.fromCode(5));
    }

    @Test
    void testFromValue() {
        assertEquals(CouponKind.WELCOME, CouponKind.fromValue("회원가입"));
        assertEquals(CouponKind.BIRTHDAY, CouponKind.fromValue("생일"));
        assertEquals(CouponKind.BOOK, CouponKind.fromValue("상품"));
        assertEquals(CouponKind.CATEGORY, CouponKind.fromValue("상품 카테고리"));
        assertEquals(CouponKind.DISCOUNT, CouponKind.fromValue("금액 할인"));

        assertThrows(IllegalArgumentException.class, () -> CouponKind.fromValue("테스트"));
    }
}

