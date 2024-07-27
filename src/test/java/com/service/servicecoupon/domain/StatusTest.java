package com.service.servicecoupon.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatusTest {

    @Test
    void testStatusEnumValues() {
        // Test values initialization
        assertEquals(0, Status.USED.getCode());
        assertEquals("사용완료", Status.USED.getValue());

        assertEquals(1, Status.AVAILABLE.getCode());
        assertEquals("사용 가능", Status.AVAILABLE.getValue());

        assertEquals(2, Status.UNAVAILABLE.getCode());
        assertEquals("사용 불가", Status.UNAVAILABLE.getValue());
    }

    @Test
    void testFromCode() {
        assertEquals(Status.USED, Status.fromCode(0));
        assertEquals(Status.AVAILABLE, Status.fromCode(1));
        assertEquals(Status.UNAVAILABLE, Status.fromCode(2));

        // Test invalid code
        assertThrows(IllegalArgumentException.class, () -> Status.fromCode(3));
    }

    @Test
    void testFromValue() {
        assertEquals(Status.USED, Status.fromValue("사용완료"));
        assertEquals(Status.AVAILABLE, Status.fromValue("사용 가능"));
        assertEquals(Status.UNAVAILABLE, Status.fromValue("사용 불가"));

        // Test invalid value
        assertThrows(IllegalArgumentException.class, () -> Status.fromValue("테스트"));
    }
}
