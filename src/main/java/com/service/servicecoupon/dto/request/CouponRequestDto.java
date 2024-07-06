package com.service.servicecoupon.dto.request;

import com.service.servicecoupon.domain.Status;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record CouponRequestDto(
    @NotNull
    long couponTypeId,
    @NotNull
    long couponPolicyId,
    @NotNull
    List<Long> clientId,
    @NotNull
    LocalDate expirationDate,
    @NotNull
    Status status) {

}
