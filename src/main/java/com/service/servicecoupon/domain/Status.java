package com.service.servicecoupon.domain;

public enum Status {
    USED(0),
    AVAILABLE(1),
    UNAVAILABLE(2);

    private final int value;

    Status(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Status fromValue(int value) {
        for (Status status : Status.values()) {
            if (status.value == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid Status value: " + value);
    }
}
