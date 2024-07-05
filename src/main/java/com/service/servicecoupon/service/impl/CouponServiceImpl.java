package com.service.servicecoupon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.servicecoupon.dto.message.SignUpClientMessageDto;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.domain.entity.ProductCategoryCoupon;
import com.service.servicecoupon.domain.entity.ProductCoupon;
import com.service.servicecoupon.dto.request.CouponRequestDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponIssuedResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.PaymentCompletedCouponResponseDto;
import com.service.servicecoupon.dto.response.RefundCouponResponseDto;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.CouponNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.CouponTypeNotFound;
import com.service.servicecoupon.exception.RabbitMessageConvertException;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.repository.ProductCategoryCouponRepository;
import com.service.servicecoupon.repository.ProductCouponRepository;
import com.service.servicecoupon.service.CouponService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class CouponServiceImpl implements CouponService {

    private final ObjectMapper objectMapper;
    private final CouponRepository couponRepository;
    private final CouponTypeRepository couponTypeRepository;
    private final CouponPolicyRepository couponPolicyRepository;
    private final ProductCouponRepository productCouponRepository;
    private final ProductCategoryCouponRepository productCategoryCouponRepository;
    private static final String ID_HEADER = "X-User-Id";

    @Transactional(rollbackFor = {CouponTypeNotFound.class, CouponPolicyNotFoundException.class})
    @Override
    public void save(CouponRequestDto couponRequest, long couponPolicyId) {
        CouponType couponType = couponTypeRepository.findById(couponRequest.couponTypeId()
        ).orElseThrow(() -> new CouponTypeNotFound("쿠폰 타입을 찾을수 없습니다."));
        CouponPolicy couponPolicy = couponPolicyRepository.findById(couponPolicyId)
            .orElseThrow(() -> new CouponPolicyNotFoundException("쿠폰 정책을 찾을수 없습니다."));
        List<Long> clientIds = couponRequest.clientId();
        for (Long clientId : clientIds) {
            Coupon coupon = new Coupon(clientId, couponType, couponPolicy,
                couponRequest.expirationDate(), couponRequest.status());
            couponRepository.save(coupon);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CouponMyPageCouponIssuedResponseDto> findByClientId(HttpHeaders httpHeaders, int page,
        int size) {
        PageRequest pageRequest = PageRequest.of(page, size,
            Sort.by(Sort.Direction.DESC, "issuedDate"));
        long clientId = NumberUtils.toLong(httpHeaders.getFirst(ID_HEADER), -1L);
        Page<Coupon> coupons = couponRepository.findByClientId(clientId, pageRequest);

        return coupons.map(coupon -> {
            CouponMyPageCouponIssuedResponseDto couponMyPageCouponIssuedResponseDto = new CouponMyPageCouponIssuedResponseDto();
            CouponPolicy couponPolicy = coupon.getCouponPolicy();
            CouponType couponType = coupon.getCouponType();
            CouponMyPageCouponIssuedResponseDto.CouponPolicy couponPolicyDto = new CouponMyPageCouponIssuedResponseDto.CouponPolicy();
            CouponMyPageCouponIssuedResponseDto.CouponType couponTypeDto = new CouponMyPageCouponIssuedResponseDto.CouponType();

            couponTypeDto.setCouponKind(couponType.getCouponKind().getValue());

            couponPolicyDto.setCouponPolicyDescription(couponPolicy.getCouponPolicyDescription());
            couponPolicyDto.setDiscountValue(couponPolicy.getDiscountValue());
            couponPolicyDto.setMinPurchaseAmount(couponPolicy.getMinPurchaseAmount());
            couponPolicyDto.setMaxDiscountAmount(couponPolicy.getMaxDiscountAmount());
            couponPolicyDto.setDiscountType(couponPolicy.getDiscountType().getValue());

            couponMyPageCouponIssuedResponseDto.setStatus(coupon.getStatus().getValue());
            couponMyPageCouponIssuedResponseDto.setIssuedDate(String.valueOf(coupon.getIssuedDate()));
            couponMyPageCouponIssuedResponseDto.setExpirationDate(String.valueOf(coupon.getExpirationDate()));
            couponMyPageCouponIssuedResponseDto.setCouponType(couponTypeDto);
            couponMyPageCouponIssuedResponseDto.setCouponPolicy(couponPolicyDto);

            return couponMyPageCouponIssuedResponseDto;
        });


    }

    @Transactional(readOnly = true)
    @Override
    public List<CouponOrderResponseDto> findClientCoupon(HttpHeaders httpHeaders) {
        CouponOrderResponseDto.ProductCoupon productCouponResponseDto = new CouponOrderResponseDto.ProductCoupon();
        CouponOrderResponseDto.CategoryCoupon productCategory = new CouponOrderResponseDto.CategoryCoupon();

        if (httpHeaders.getFirst(ID_HEADER) == null) {
            throw new ClientNotFoundException("clientId is null");
        }
        List<Coupon> coupons = couponRepository.findAvailableCouponsByClientId(
            NumberUtils.toLong(httpHeaders.getFirst(ID_HEADER), -1L));

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
            ProductCoupon productCoupon = productCouponRepository.findByProductPolicy_CouponPolicyId(
                couponPolicy.getCouponPolicyId());
            if (productCoupon != null) {
                productCouponResponseDto.setProductId(productCoupon.getProductId());
                couponOrderResponseDto.setProductCoupon(productCouponResponseDto);
                couponOrderResponseDto.setCategoryCoupon(null);
            } else {
                couponOrderResponseDto.setProductCoupon(null);
            }
            // 카테고리 쿠폰 정보 설정
            ProductCategoryCoupon productCategoryCoupon = productCategoryCouponRepository.findByCategoryPolicy_CouponPolicyId(
                couponPolicy.getCouponPolicyId());
            if (productCategoryCoupon != null) {
                productCategory.setProductCategoryId(productCategoryCoupon.getProductCategoryId());
                couponOrderResponseDto.setCategoryCoupon(productCategory);
                couponOrderResponseDto.setProductCoupon(null);
            } else {
                couponOrderResponseDto.setCategoryCoupon(null);
            }

            return couponOrderResponseDto;
        }).toList();
    }

    @Transactional(rollbackFor = {CouponPolicyNotFoundException.class})
    @Override
    public void paymentCompletedCoupon(PaymentCompletedCouponResponseDto paymentCompletedCouponResponseDto) {
        Coupon coupon = couponRepository.findById(paymentCompletedCouponResponseDto.getCouponId())
            .orElseThrow(() -> new CouponNotFoundException("쿠폰이 존재하지 않습니다.'"));
        Objects.requireNonNull(coupon).setUsedDate(LocalDate.now());
        coupon.setStatus(Status.USED);
        couponRepository.save(coupon);
    }

    @Override
    @RabbitListener(queues = "${rabbit.login.queue.name}")
    public void payWelcomeCoupon(String message) {
        log.error("message{}", message);
        SignUpClientMessageDto signUpClientMessageDto;
        try {
            signUpClientMessageDto = objectMapper.readValue(message, SignUpClientMessageDto.class);
        } catch (IOException e) {
            throw new RabbitMessageConvertException("회원가입 유저의 메세지 변환에 실패했습니다.");
        }
        CouponPolicy couponPolicy = couponPolicyRepository.findTopByCouponPolicyDescriptionContainingOrderByIdDesc(
            "생일");
        if (couponPolicy == null) {
            throw new CouponPolicyNotFoundException("쿠폰정책을 찾을수 없습니다.");
        }

        CouponType couponType = couponTypeRepository.findByCouponKind(CouponKind.WELCOME);
        Coupon coupon = new Coupon(signUpClientMessageDto.getClientId(), couponType, couponPolicy,
            LocalDate.now().plusDays(30), Status.AVAILABLE);
        couponRepository.save(coupon);
    }

    @Transactional(rollbackFor = {CouponNotFoundException.class})
    @Override
    public void refundCoupon(RefundCouponResponseDto refundCouponResponseDto) {

        Coupon coupon = couponRepository.findById(refundCouponResponseDto.getCouponId())
            .orElseThrow(() -> new CouponNotFoundException("쿠폰이 존재하지 않습니다."));
        if (coupon.getStatus().equals(Status.UNAVAILABLE) || coupon.getStatus().equals(Status.USED)) {
            return;
        }
        Objects.requireNonNull(coupon).setUsedDate(null);
        coupon.setStatus(Status.AVAILABLE);

        couponRepository.save(coupon);
    }
}

