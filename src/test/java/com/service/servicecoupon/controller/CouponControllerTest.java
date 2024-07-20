package com.service.servicecoupon.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.service.servicecoupon.controller.impl.CouponControllerImpl;
import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.dto.request.CouponRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponAdminPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponMyPageCouponResponseDto;
import com.service.servicecoupon.dto.response.CouponOrderResponseDto;
import com.service.servicecoupon.exception.ClientNotFoundException;
import com.service.servicecoupon.exception.CouponPolicyNotFoundException;
import com.service.servicecoupon.exception.CouponTypeNotFoundException;
import com.service.servicecoupon.service.CouponService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class CouponControllerImplTest {

    @InjectMocks
    private CouponControllerImpl couponController;

    @Mock
    private CouponService couponService;

    public CouponControllerImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindClientCoupon() {
        HttpHeaders headers = new HttpHeaders();
        CouponOrderResponseDto dto = new CouponOrderResponseDto();
        when(couponService.findClientCoupon(headers)).thenReturn(Collections.singletonList(dto));

        List<CouponOrderResponseDto> response = couponController.findClientCoupon(headers);

        assertThat(response).isNotNull();
        assertThat(response).hasSize(1);
    }

    @Test
    void testFindMyPageCoupons() {
        HttpHeaders headers = new HttpHeaders();
        Page<CouponMyPageCouponResponseDto> page = new PageImpl<>(Collections.singletonList(new CouponMyPageCouponResponseDto()), PageRequest.of(0, 1), 1);
        when(couponService.findByClientId(headers, 0, 1, Status.AVAILABLE)).thenReturn(page);

        ResponseEntity<Page<CouponMyPageCouponResponseDto>> response = couponController.findMyPageCoupons(headers, 0, 1, Status.AVAILABLE);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSize(1);
    }

    @Test
    void testSaveCoupon() {
        List<Long> clients = new ArrayList<>();
        clients.add(1L);
        CouponRegisterRequestDto dto = new CouponRegisterRequestDto(1L,1L,clients, LocalDate.now(), Status.AVAILABLE);


        ResponseEntity<CouponRegisterRequestDto> response = couponController.saveCoupon(1L, dto);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void testFindUserCoupons() {
        Page<CouponAdminPageCouponResponseDto> page = new PageImpl<>(Collections.singletonList(new CouponAdminPageCouponResponseDto()), PageRequest.of(0, 1), 1);
        when(couponService.findByAllCoupon(0, 1, Status.AVAILABLE)).thenReturn(page);

        ResponseEntity<Page<CouponAdminPageCouponResponseDto>> response = couponController.findUserCoupons(0, 1, Status.AVAILABLE);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSize(1);
    }

    @Test
    void testHandleClientNotFoundException() {
        ClientNotFoundException ex = new ClientNotFoundException("Client not found");

        ResponseEntity<String> response = couponController.handleExceptionClientNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Client not found");
    }

    @Test
    void testHandleCouponNotFoundException() {
        ClientNotFoundException ex = new ClientNotFoundException("Client not found");

        ResponseEntity<String> response = couponController.handleCouponNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Client not found");
    }

    @Test
    void testHandleCouponPolicyNotFoundException() {
        CouponPolicyNotFoundException ex = new CouponPolicyNotFoundException("Coupon policy not found");

        ResponseEntity<String> response = couponController.handleCouponPolicyNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Coupon policy not found");
    }

    @Test
    void testHandleCouponTypeNotFound() {
        CouponTypeNotFoundException ex = new CouponTypeNotFoundException("Coupon type not found");

        ResponseEntity<String> response = couponController.handleCouponTypeNotFound(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Coupon type not found");
    }
}
