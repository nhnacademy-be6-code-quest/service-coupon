package com.service.servicecoupon.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.servicecoupon.domain.CouponKind;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.Coupon;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.dto.message.SignUpClientMessageDto;
import com.service.servicecoupon.dto.request.CouponRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.dto.response.RefundCouponResponseDto;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.repository.CouponPolicyRepository;
import com.service.servicecoupon.repository.CouponRepository;
import com.service.servicecoupon.repository.CouponTypeRepository;
import com.service.servicecoupon.repository.ProductCategoryCouponRepository;
import com.service.servicecoupon.repository.ProductCouponRepository;
import com.service.servicecoupon.service.impl.CouponServiceImpl;
import java.io.IOException;
import java.lang.reflect.Field;
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
       Coupon coupon1 = new Coupon(1L, new CouponType(1L, CouponKind.WELCOME),
                new CouponPolicy("Policy description", DiscountType.PERCENTAGEDISCOUNT, 10, 0,
                    1000),
                LocalDate.now().plusDays(10), Status.AVAILABLE);
setField(coupon1,"couponId",1L);
        Page<Coupon> clientPage = new PageImpl<>(Collections.singletonList(coupon1));
        when(couponRepository.findAll(any(PageRequest.class))).thenReturn(clientPage);


        // When
        Page<CouponAdminPageCouponResponseDto> response = couponService.findByAllCoupon(page, size);

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
        Coupon coupon1 = new Coupon(1L, new CouponType(1L, CouponKind.WELCOME),
            new CouponPolicy("Policy description", DiscountType.AMOUNTDISCOUNT, 10, 0, 1000),
            LocalDate.now().plusDays(10), Status.AVAILABLE);
        coupon1.setUsedDate(LocalDate.now());
        Page<Coupon> clientPage = new PageImpl<>(List.of(coupon1));
        when(couponRepository.findByClientId(eq(clientId), any(PageRequest.class))).thenReturn(clientPage);

        // When
        Page<CouponMyPageCouponResponseDto> response = couponService.findByClientId(headers, page, size);

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
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response).hasSize(2);
    }
    @Test
    void testPayWelcomeCoupon_Success()
        throws IOException, NoSuchFieldException, IllegalAccessException {
        // Arrange
        String message = "{\"clientId\":\"123\"}";
        SignUpClientMessageDto signUpClientMessageDto = new SignUpClientMessageDto();
        Field clientIdField = SignUpClientMessageDto.class.getDeclaredField("clientId");
        clientIdField.setAccessible(true);
        clientIdField.set(signUpClientMessageDto, 1L);
        CouponPolicy couponPolicy = new CouponPolicy( "생일",DiscountType.PERCENTAGEDISCOUNT,0, 10000,10000);
        CouponType couponType = new CouponType(1L, CouponKind.WELCOME);
        Coupon coupon = new Coupon(123L, couponType, couponPolicy, LocalDate.now().plusDays(30), Status.AVAILABLE);

        when(objectMapper.readValue(message, SignUpClientMessageDto.class)).thenReturn(signUpClientMessageDto);
        when(couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc("생일")).thenReturn(couponPolicy);
        when(couponTypeRepository.findByCouponKind(CouponKind.WELCOME)).thenReturn(couponType);
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        // Act
        couponService.payWelcomeCoupon(message);

        // Assert
        verify(couponRepository, times(1)).save(any(Coupon.class));
    }

    @Test
    void testPayWelcomeCoupon_CouponPolicyNotFound() throws JsonProcessingException {
        // Arrange
        String message = "{\"clientId\":\"123\"}";
        when(objectMapper.readValue(message, SignUpClientMessageDto.class)).thenReturn(new SignUpClientMessageDto());
        when(couponPolicyRepository.findTop1ByCouponPolicyDescriptionContainingOrderByCouponPolicyIdDesc("생일")).thenReturn(null);

        // Act and Assert
        assertThrows(CouponPolicyNotFoundException.class, () -> couponService.payWelcomeCoupon(message));
    }



    @Test
    void testRefundCoupon_CouponExistsAndIsUnused() {
        // Mock data
        Long couponId = 1L;
        RefundCouponResponseDto refundDto = new RefundCouponResponseDto();
        setField(refundDto,"couponId",1L);

        Coupon coupon = new Coupon();
        setField(coupon,"couponId",1L);
        coupon.setStatus(Status.USED);

        // Mock couponRepository.findById
        when(couponRepository.findById(couponId)).thenReturn(Optional.of(coupon));

        // Call the method
        assertDoesNotThrow(() -> couponService.refundCoupon(refundDto));

        // Verify couponRepository.findById is called once with argument 1L
        verify(couponRepository, times(1)).findById(couponId);

        // Verify couponRepository.save is called once with the same coupon object
        verify(couponRepository, times(1)).save(coupon);

        // Additional assertions if needed
        assertEquals(Status.AVAILABLE, coupon.getStatus());
        assertNull(coupon.getUsedDate());
    }

}
