package com.service.servicecoupon.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@AllArgsConstructor
public class ProductCategoryCouponResponseDto {
    private long productCategoryId;
}
