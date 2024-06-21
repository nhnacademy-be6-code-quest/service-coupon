package com.service.servicecoupon.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.repository.CouponTypeRepository;


@RequiredArgsConstructor
@Service
public class CouponTypeService {

    private final CouponTypeRepository couponTypeRepository;

    public List<CouponType> findAllCouponType(){
        return couponTypeRepository.findAll();

    }
}
//    public CouponType save(CouponType couponType){
//        return couponTypeRepository.save(couponType);
//    }
//    public CouponType findById(long id){
//        return couponTypeRepository.findById(id).orElse(null);
//    }
//
//    public void deleteCouponType(long id){
//        couponTypeRepository.deleteById(id);
//    }


