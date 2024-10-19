package com.example.HomeLoanApp.homeloanapplication.controller;

import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloanapplication.service.HomeLoanApplicationService;
import com.example.HomeLoanApp.homeloanofferings.dto.HomeLoanApplicationDto;
import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.service.HomeLoanOfferingService;
import com.example.HomeLoanApp.homeloansecurity.auth.SecurityConfig;
import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HomeLoanApplicationController.class, includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
public class HomeLoanApplicationControllerTest {

    @MockBean
    private HomeLoanOfferingService loanOfferingService;

    @MockBean
    private HomeLoanApplicationService loanApplicationService;

    @MockBean
    private TokenManager tokenManager;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @WithMockUser
    public void testGetAllLoanOfferings() throws Exception {
        HomeLoanOffering product1 = new HomeLoanOffering();
        product1.setId(1L);
        product1.setName("Save 20%");
        product1.setDescription("Minimal Interest Rate");
        product1.setInterestRate(8.5);
        HomeLoanOffering product2 = new HomeLoanOffering();
        product2.setId(2L);
        product2.setName("Save 30%");
        product2.setDescription("Minimal Interest Rate");
        product2.setInterestRate(8.5);
        List<HomeLoanOffering> expectedProducts = Arrays.asList(product1, product2);
        given(loanOfferingService.findAllLoanOfferings()).willReturn(expectedProducts);
        mockMvc.perform(get("/api/home-loans/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedProducts.size()))
                .andExpect(jsonPath("$[0].id").value(product1.getId()))
                .andExpect(jsonPath("$[0].name").value(product1.getName()))
                .andExpect(jsonPath("$[0].description").value(product1.getDescription()))
                .andExpect(jsonPath("$[0].interestRate").value(product1.getInterestRate()))
                .andExpect(jsonPath("$[1].id").value(product2.getId()))
                .andExpect(jsonPath("$[1].name").value(product2.getName()))
                .andExpect(jsonPath("$[1].description").value(product2.getDescription()))
                .andExpect(jsonPath("$[1].interestRate").value(product2.getInterestRate()));
    }

    @Test
    @WithMockUser
    void whenApplyForLoan_thenReturnCreatedApplication() throws Exception {
        HomeLoanApplicationDto applicationDto = new HomeLoanApplicationDto();
        HomeLoanApplication expectedApplication = new HomeLoanApplication();
        given(loanApplicationService.applyForLoan(eq(applicationDto), any(String.class))).willReturn(expectedApplication);
        mockMvc.perform(post("/api/home-loans/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicationDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(expectedApplication.getId()));
    }

    @Test
    @WithMockUser
    void whenCalculateEmi_thenReturnEmiValue() throws Exception {
        Double loanAmount = 100000.00;
        Double interestRate = 10.5;
        Integer tenureYears = 10;
        Double expectedEmi = 1348.97;
        given(loanApplicationService.calculateEmi(anyDouble(), anyDouble(), anyInt())).willReturn(expectedEmi);
        mockMvc.perform(get("/api/home-loans/calculate-emi")
                        .param("loanAmount", loanAmount.toString())
                        .param("interestRate", interestRate.toString())
                        .param("tenureYears", tenureYears.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(expectedEmi));
    }
}

