package com.service.servicecoupon.domain.entity;

import com.service.servicecoupon.domain.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long couponId;
    @ManyToOne
    private CouponType couponType;
    @ManyToOne
    private CouponPolicy couponPolicy;
    private long clientId;
    private Date issuedDate;
    private Date expirationDate;
    @Column(nullable = false)
    private Date usedDate;
    private Status status;

}
