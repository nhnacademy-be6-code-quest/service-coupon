package com.service.servicecoupon.domain.entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.service.servicecoupon.domain.CouponKind;


@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CouponType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponTypeId;
    @Column(name="couponType")
    private CouponKind couponKind;

}
