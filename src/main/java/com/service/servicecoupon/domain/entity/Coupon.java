package com.service.servicecoupon.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.service.servicecoupon.domain.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issuedDate;
    private LocalDate expirationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Setter
    private LocalDate usedDate;
    @Setter
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
