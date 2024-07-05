package com.service.servicecoupon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.servicecoupon.config.SignUpClientMessageDto;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.*;
import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.*;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.RabbitMessageConvertException;
import com.service.servicecoupon.repository.*;
import com.service.servicecoupon.service.CouponService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Slf4j
public class CouponServiceImpl implements CouponService{

    private final ObjectMapper objectMapper;
    private final CouponRepository couponRepository;
    private final CouponTypeRepository couponTypeRepository;
    private final CouponPolicyRepository couponPolicyRepository;
    private final ProductCouponRepository productCouponRepository;
    private final ProductCategoryCouponRepository productCategoryCouponRepository;
    private static final String ID_HEADER = "X-User-Id";

    @Override
    public void save(CouponRequestDto couponRequest, long couponPolicyId){
        CouponType couponType = couponTypeRepository.findById(couponRequest.couponTypeId()
        ).orElseThrow();
        CouponPolicy couponPolicy = couponPolicyRepository.findById(couponPolicyId).orElseThrow();
        List<Long> clientIds = couponRequest.clientId();
        for (Long clientId : clientIds){
            Coupon coupon=new Coupon(clientId,couponType,couponPolicy,couponRequest.expirationDate(),couponRequest.status());
            couponRepository.save(coupon);
        }
    }

    @Override
    public Page<CouponResponseDto> findByClientId(HttpHeaders httpHeaders, int page, int size) {
        return null;
    }

    @Override
    public List<CouponOrderResponseDto> findClientCoupon(HttpHeaders httpHeaders) {
        CouponOrderResponseDto.ProductCoupon productCouponResponseDto = new CouponOrderResponseDto.ProductCoupon();
        CouponOrderResponseDto.CategoryCoupon productCategory = new CouponOrderResponseDto.CategoryCoupon();

        if (httpHeaders.getFirst(ID_HEADER) == null){
            throw new ClientNotFoundException("clientId is null");
        }
        List<Coupon> coupons = couponRepository.findAvailableCouponsByClientId(Long.parseLong(httpHeaders.getFirst(ID_HEADER)));

        return coupons.stream().map(coupon -> {
            CouponOrderResponseDto couponOrderResponseDto = new CouponOrderResponseDto();
            CouponOrderResponseDto.CouponPolicy couponPolicyDto = new CouponOrderResponseDto.CouponPolicy();
            CouponPolicy couponPolicy = coupon.getCouponPolicy();

            couponOrderResponseDto.setCouponId(coupon.getCouponId());
            couponPolicyDto.setCouponPolicyDescription(couponPolicy.getCouponPolicyDescription());
            couponPolicyDto.setDiscountType(String.valueOf(couponPolicy.getDiscountType()));
            couponPolicyDto.setDiscountValue(couponPolicy.getDiscountValue());
            couponPolicyDto.setMinPurchaseAmount(couponPolicy.getMinPurchaseAmount());
            couponPolicyDto.setMaxDiscountAmount(couponPolicy.getMaxDiscountAmount());
            couponOrderResponseDto.setCouponPolicy(couponPolicyDto);

            // 상품 쿠폰 정보 설정
            ProductCoupon productCoupon = productCouponRepository.findByProductPolicy_CouponPolicyId(couponPolicy.getCouponPolicyId());
            if (productCoupon != null) {
                productCouponResponseDto.setProductId(productCoupon.getProductId());
                couponOrderResponseDto.setProductCoupon(productCouponResponseDto);
                couponOrderResponseDto.setCategoryCoupon(null);
            }
            else{
                couponOrderResponseDto.setProductCoupon(null);
            }
            // 카테고리 쿠폰 정보 설정
            ProductCategoryCoupon productCategoryCoupon = productCategoryCouponRepository.findByCategoryPolicy_CouponPolicyId(couponPolicy.getCouponPolicyId());
            if (productCategoryCoupon != null) {
                productCategory.setProductCategoryId(productCategoryCoupon.getProductCategoryId());
                couponOrderResponseDto.setCategoryCoupon(productCategory);
                couponOrderResponseDto.setProductCoupon(null);
            }
           else{
                couponOrderResponseDto.setCategoryCoupon(null);
            }

            return couponOrderResponseDto;
        }).collect(Collectors.toList());
    }



    @Override
    @Transactional
    public void update(long couponId){
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        coupon.setUsedDate(LocalDate.now());
        coupon.setStatus(Status.USED);
        couponRepository.save(coupon);
    }


//            clientLoginMessageDto = objectMapper.readValue(message , ClientLoginMessageDto.class);
//        } catch (IOException e) {
//            throw new RabbitMessageConvertException("Failed to convert message to ClientLoginMessageDto");
//        }
//        Client client = clientRepository.findById(clientLoginMessageDto.getClientId()).orElse(null);
//        if (client == null || client.isDeleted()) {
//            throw new NotFoundClientException("Not found : " + clientLoginMessageDto.getClientId());
//        }
//        log.info("success update login");
//        client.setLastLoginDate(clientLoginMessageDto.getLastLoginDate());
//        clientRepository.save(client);
//    }

    @Override
    @RabbitListener(queues = "${rabbit.login.queue.name}")
    public void payWelcomeCoupon(String message){
        log.error("message{}",message);
        SignUpClientMessageDto signUpClientMessageDto = new SignUpClientMessageDto();
        try{
            signUpClientMessageDto = objectMapper.readValue(message, SignUpClientMessageDto.class);
        } catch(IOException e) {
//            throw new RabbitMessageConvertException("회원가입 유저의 메세지 변환에 실패했습니다.");
            System.out.println("1");
        }
        CouponPolicy couponPolicy = CouponPolicy.builder()
                .couponPolicyDescription("50,000 이상 구매 시 10,000 할인 쿠폰")
                .discountValue(10000)
                .maxDiscountAmount(10000)
                .minPurchaseAmount(50000)
                .discountType(DiscountType.AMOUNTDISCOUNT)
                .build();

        couponPolicyRepository.save(couponPolicy);
        CouponType couponType = couponTypeRepository.findByCouponKind(CouponKind.WELCOME);
        Coupon coupon = new Coupon(signUpClientMessageDto.getClientId(), couponType, couponPolicy,LocalDate.now().plusDays(30), Status.AVAILABLE);
        couponRepository.save(coupon);
    }

    @Override
    public void refundCoupon(long couponId){
        Coupon coupon = couponRepository.findById(couponId).orElse(null);
        coupon.setUsedDate(null);
        coupon.setStatus(Status.AVAILABLE);

        couponRepository.save(coupon);
    }
}

