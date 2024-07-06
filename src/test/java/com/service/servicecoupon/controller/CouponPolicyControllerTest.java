package com.service.servicecoupon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import com.service.servicecoupon.controller.CouponPolicyController;
import com.service.servicecoupon.controller.impl.CouponPolicyControllerImpl;
import com.service.servicecoupon.domain.DiscountType;
import com.service.servicecoupon.dto.request.CouponPolicyRegisterRequestDto;
import com.service.servicecoupon.dto.response.CouponPolicyListResponseDto;
import com.service.servicecoupon.service.CouponPolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(CouponPolicyControllerImpl.class)

class CouponPolicyControllerTest {

    @MockBean
    private CouponPolicyService couponPolicyService;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();    }

    //@Test
//    void testFindAllCouponPolicy() throws Exception {
//        // Given
//        int page = 0;
//        int size = 10;
//
//        // Create a sample CouponPolicyListResponseDto object
//        CouponPolicyListResponseDto content = new CouponPolicyListResponseDto(1L, "Policy 1", DiscountType.AMOUNTDISCOUNT.getValue(), 10000, 0, 10000);
//
//        // Create a Page object that simulates the returned page of results
//        Page<CouponPolicyListResponseDto> pageResponse = new PageImpl<>(List.of(content));
//
//        // Mock the couponPolicyService.getPolicies() method call to return the Page object
//        when(couponPolicyService.getPolicies(page, size)).thenReturn(pageResponse);
//
//        // When and Then
//        mockMvc.perform(get("/api/coupon/policy")
//                .param("page", String.valueOf(page))
//                .param("size", String.valueOf(size)))
//            .andExpect(status().isOk()) // Verify that HTTP status is 200 OK
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Verify content type is JSON
//            .andExpect(jsonPath("$.content").isArray()) // Verify that 'content' field in JSON response is an array
//            .andExpect(jsonPath("$.content[0].couponPolicyId").value(content.getCouponPolicyId()))
//            .andExpect(jsonPath("$.content[0].couponPolicyDescription").value(content.getCouponPolicyDescription()))
//            .andExpect(jsonPath("$.content[0].discountType").value(content.getDiscountType()))
//            .andExpect(jsonPath("$.content[0].discountValue").value(content.getDiscountValue()))
//            .andExpect(jsonPath("$.content[0].minPurchaseAmount").value(content.getMinPurchaseAmount()))
//            .andExpect(jsonPath("$.content[0].maxDiscountAmount").value(content.getMaxDiscountAmount()));
//    }



    @Test
    void testSaveCouponPolicy() throws Exception {
        // Given
        CouponPolicyRegisterRequestDto requestDto = new CouponPolicyRegisterRequestDto(
            "Test Policy", DiscountType.PERCENTAGEDISCOUNT, 100, 0, 10000, -1L, null);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(requestDto);

        // When
        mockMvc.perform(post("/api/coupon/policy/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isCreated());

        // Then
        verify(couponPolicyService).save(requestDto);
    }
}

