package com.service.servicecoupon.domain.entity;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;
import com.service.servicecoupon.domain.Status;


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
