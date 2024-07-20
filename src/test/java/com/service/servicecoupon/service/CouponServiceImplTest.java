package com.service.servicecoupon.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.dto.message.RefundCouponMessageDto;
import com.service.servicecoupon.dto.message.SignUpClientMessageDto;
import com.service.servicecoupon.dto.request.CouponRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.PaymentCompletedCouponResponseDto;
import com.service.servicecoupon.exception.CouponNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.RabbitMessageConvertException;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.repository.ProductCategoryCouponRepository;
import com.service.servicecoupon.repository.ProductCouponRepository;
import com.service.servicecoupon.service.impl.CouponServiceImpl;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;

class CouponServiceImplTest {

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private CouponTypeRepository couponTypeRepository;

    @Mock
    private CouponPolicyRepository couponPolicyRepository;

    @Mock
    private ProductCouponRepository productCouponRepository;

    @Mock
    private ProductCategoryCouponRepository productCategoryCouponRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CouponServiceImpl couponService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Mock data
        List<Long> clients = new ArrayList<>();
        long client = 1L;
        clients.add(client);
        CouponRegisterRequestDto requestDto = new CouponRegisterRequestDto(1L, 1L, clients, LocalDate.now(),
            Status.AVAILABLE);

        CouponType couponType = new CouponType(1L, CouponKind.BIRTHDAY);
        when(couponTypeRepository.findById(any())).thenReturn(java.util.Optional.of(couponType));

        CouponPolicy couponPolicy = new CouponPolicy(1L, "Test Policy", DiscountType.AMOUNTDISCOUNT,
            10000, 50000, 10000);
        when(couponPolicyRepository.findById(any())).thenReturn(
            java.util.Optional.of(couponPolicy));

        // Call the method
        assertDoesNotThrow(() -> couponService.save(requestDto, 1L));

        // Verify couponRepository.save is called once
        verify(couponRepository, times(1)).save(any());
    }

    @Test
    void testFindByAllCoupon() {
        // Given
        int page = 0;
        int size = 10;
        Status status = Status.AVAILABLE;
       Coupon coupon1 = new Coupon(1L, new CouponType(1L, CouponKind.WELCOME),
                new CouponPolicy("Policy description", DiscountType.PERCENTAGEDISCOUNT, 10, 0,
                    1000),
                LocalDate.now().plusDays(10), Status.AVAILABLE);
setField(coupon1,"couponId",1L);
        Page<Coupon> clientPage = new PageImpl<>(Collections.singletonList(coupon1));
        when(couponRepository.findAllByStatus(any(PageRequest.class),eq(status))).thenReturn(clientPage);


        // When
        Page<CouponAdminPageCouponResponseDto> response = couponService.findByAllCoupon(page, size, status);

        // Then
Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getContent()).hasSize(1);
    }


    @Test
    void testFindByClientId() {
        // Given
        long clientId = 1L;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id", String.valueOf(clientId));
        int page = 0;
        int size = 10;
        Status status = Status.AVAILABLE;
        Coupon coupon1 = new Coupon(1L, new CouponType(1L, CouponKind.WELCOME),
            new CouponPolicy("Policy description", DiscountType.AMOUNTDISCOUNT, 10, 0, 1000),
            LocalDate.now().plusDays(10), Status.AVAILABLE);
        coupon1.setUsedDate(LocalDate.now());
        Page<Coupon> clientPage = new PageImpl<>(List.of(coupon1));
        when(couponRepository.findByClientIdAndStatus(eq(clientId), any(PageRequest.class), eq(status))).thenReturn(clientPage);

        // When
        Page<CouponMyPageCouponResponseDto> response = couponService.findByClientId(headers, page, size, status);

        // Then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getContent()).hasSize(1);
    }


    @Test
    void testFindClientOrderCoupon() {
        // Given
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-User-Id", "1L"); // Set a valid client ID header
        List<Coupon> coupons = List.of(
            new Coupon(1L, new CouponType(1L, CouponKind.WELCOME),
                new CouponPolicy("Policy description", DiscountType.AMOUNTDISCOUNT, 10, 0, 1000),
                LocalDate.now().plusDays(10), Status.AVAILABLE),
            new Coupon(1L, new CouponType(2L, CouponKind.WELCOME),
                new CouponPolicy("Policy description", DiscountType.AMOUNTDISCOUNT, 10, 0, 1000),
                LocalDate.now().plusDays(10), Status.AVAILABLE));

        when(couponRepository.findAvailableCouponsByClientId(anyLong())).thenReturn(coupons);

        // When
        List<CouponOrderResponseDto> response = couponService.findClientCoupon(headers);

        // Then
        Assertions.assertThat(response).hasSize(2);
    }

    @Test
    void testPaymentCompletedCoupon_Success() throws IOException {
        // Given
        String message = "{\"couponId\": 1}";
        PaymentCompletedCouponResponseDto dto = new PaymentCompletedCouponResponseDto();
        dto.setCouponId(1L);

        Coupon coupon = new Coupon();
        coupon.setUsedDate(null);
        coupon.setStatus(Status.AVAILABLE);

        when(objectMapper.readValue(eq(message), any(Class.class))).thenReturn(dto);
        when(couponRepository.findById(dto.getCouponId())).thenReturn(Optional.of(coupon));

        // When
        couponService.paymentCompletedCoupon(message);

        // Then
        verify(couponRepository).findById(dto.getCouponId());
        verify(couponRepository).save(coupon);
        assertThat(coupon.getUsedDate()).isEqualTo(LocalDate.now());
        assertThat(coupon.getStatus()).isEqualTo(Status.USED);
    }


    @Test
    void testPaymentCompletedCoupon_CouponNotFound() throws IOException {
        // Given
        String message = "{\"couponId\": 1}";
        PaymentCompletedCouponResponseDto dto = new PaymentCompletedCouponResponseDto();
        dto.setCouponId(1L);

        when(objectMapper.readValue(eq(message), any(Class.class))).thenReturn(dto);
        when(couponRepository.findById(dto.getCouponId())).thenReturn(Optional.empty());

        // When
        // Then
        assertThrows(CouponNotFoundException.class, () -> couponService.paymentCompletedCoupon(message));
    }


    @Test
    void testPayWelcomeCoupon_Success() throws IOException {
        // Given
        String message = "{\"clientId\": 1}";
        SignUpClientMessageDto dto = new SignUpClientMessageDto();
        dto.setClientId(1L);

        CouponPolicy couponPolicy = new CouponPolicy();
        CouponType couponType = new CouponType();

        when(objectMapper.readValue(eq(message), any(Class.class))).thenReturn(dto);
        when(couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc("회원"))
            .thenReturn(couponPolicy);
        when(couponTypeRepository.findByCouponKind(CouponKind.WELCOME)).thenReturn(couponType);

        // When
        couponService.payWelcomeCoupon(message);

        // Then
        verify(couponRepository).save(any(Coupon.class));
        // Additional assertions can be made if necessary
    }


    @Test
    void testPayWelcomeCoupon_CouponPolicyNotFound() throws IOException {
        // Given
        String message = "{\"clientId\": 1}";
        SignUpClientMessageDto dto = new SignUpClientMessageDto();
        dto.setClientId(1L);

        when(objectMapper.readValue(eq(message), any(Class.class))).thenReturn(dto);
        when(couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc("회원"))
            .thenReturn(null);

        // When
        // Then
        assertThrows(CouponPolicyNotFoundException.class, () -> couponService.payWelcomeCoupon(message));
    }

    @Test
    void testRefundCoupon_Success() throws IOException {
        // Given
        String message = "{\"couponId\": 1}";
        RefundCouponMessageDto dto = new RefundCouponMessageDto();
        dto.setCouponId(1L);

        Coupon coupon = new Coupon();
        coupon.setStatus(Status.USED);

        when(objectMapper.readValue(eq(message), eq(RefundCouponMessageDto.class))).thenReturn(dto);
        when(couponRepository.findById(dto.getCouponId())).thenReturn(Optional.of(coupon));

        // When
        couponService.refundCoupon(message);

        // Then
        verify(couponRepository).save(coupon); // Ensure save is called
        assertThat(coupon.getStatus()).isEqualTo(Status.AVAILABLE); // Ensure the coupon status is updated
    }

    @Test
    void testRefundCoupon_CouponNotFound() throws IOException {
        // Given
        String message = "{\"couponId\": 1}";
        RefundCouponMessageDto dto = new RefundCouponMessageDto();
        dto.setCouponId(1L);

        when(objectMapper.readValue(eq(message), any(Class.class))).thenReturn(dto);
        when(couponRepository.findById(dto.getCouponId())).thenReturn(Optional.empty());

        // When
        // Then
        assertThrows(CouponNotFoundException.class, () -> couponService.refundCoupon(message));
    }

    @Test
    void testDlqRefundCoupon() {
        // Given
        String message = "Sample DLQ message";

        // When
        couponService.dlqRefundCoupon(message);

        // Then
        // Verify that log message is correct
        // Since logging isn't directly testable with standard assertions,
        // use a logging framework or logger spy to verify if necessary
    }



}
