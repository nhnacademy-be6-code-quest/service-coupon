package com.service.servicecoupon.controller;

import com.service.servicecoupon.domain.Status;
import com.service.servicecoupon.domain.entity.CouponPolicy;
import com.service.servicecoupon.domain.entity.CouponType;
import com.service.servicecoupon.domain.request.CouponRequestDto;
import com.service.servicecoupon.domain.response.CouponResponseDto;
import com.service.servicecoupon.service.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponController.class)
public class CouponControllerTest {

    @MockBean
    private CouponService couponService;

    @InjectMocks
    private CouponController couponController;

    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(couponController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCouponFind() throws Exception {
        long clientId = 1L;

        CouponResponseDto coupon1 =  CouponResponseDto.builder()
                .couponId(1L)
                .couponType(new CouponType())
                .couponPolicy(new CouponPolicy())
                .issuedDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now())
                .clientId(1L)
                .status(Status.USED)
                .build();
        CouponResponseDto coupon2 = CouponResponseDto.builder()
                .couponId(2L)
                .couponType(new CouponType())
                .couponPolicy(new CouponPolicy())
                .clientId(1L)
                .issuedDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now())
                .status(Status.AVAILABLE)
                .build();
        List<CouponResponseDto> coupons = Arrays.asList(coupon1, coupon2);

        when(couponService.findByClientId(clientId)).thenReturn(coupons);

        mockMvc.perform(get("/coupon/{clientId}", clientId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(coupons)));
    }

    @Test
    public void testSaveCoupon() throws Exception {
        long couponPolicyId = 1L;
        CouponRequestDto couponRequest = new CouponRequestDto(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), Status.USED);

        mockMvc.perform(post("/admin/coupon/register/{couponPolicyId}", couponPolicyId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(couponRequest)))
                .andExpect(status().isCreated());
    }
}
