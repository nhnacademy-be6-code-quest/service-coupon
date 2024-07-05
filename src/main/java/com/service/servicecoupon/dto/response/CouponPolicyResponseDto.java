package com.service.servicecoupon.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.repository.ProductCouponRepository;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CouponPolicyResponseDto {

    private long couponPolicyId;


    }