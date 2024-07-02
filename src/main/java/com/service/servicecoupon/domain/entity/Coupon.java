package com.service.servicecoupon.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JoinColumn(name="couponTypeId")
    private CouponType couponType;
    @ManyToOne
    @JoinColumn(name="couponPolicyId")
    private CouponPolicy couponPolicy;
    private long clientId;
    @Column(columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime issuedDate;
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime expirationDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "DATETIME")
    private LocalDateTime usedDate;
    private Status status;


    public Coupon(Long clientId,CouponType couponType,CouponPolicy couponPolicy,LocalDateTime expirationDate,Status status){
        this.couponType=couponType;
        this.couponPolicy=couponPolicy;
        this.clientId=clientId;
        this.issuedDate=LocalDateTime.now();
        this.expirationDate=expirationDate;
        this.status=status;
    }
}
