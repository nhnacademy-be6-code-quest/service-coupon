package com.service.servicecoupon.domain.entity;

import java.time.LocalDate;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;
    @Column(columnDefinition = "DATETIME")
    private LocalDate expirationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(columnDefinition = "DATETIME")
    private LocalDate usedDate;
    private Status status;

    @Builder
    public Coupon(Long clientId,CouponType couponType,CouponPolicy couponPolicy,LocalDate expirationDate,Status status){
        this.couponType=couponType;
        this.couponPolicy=couponPolicy;
        this.clientId=clientId;
        this.issuedDate=LocalDate.now();
        this.expirationDate=expirationDate;
        this.status=status;
    }
}
