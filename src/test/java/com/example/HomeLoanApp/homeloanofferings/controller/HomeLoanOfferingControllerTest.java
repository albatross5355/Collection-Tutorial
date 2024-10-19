package com.example.HomeLoanApp.homeloanofferings.controller;

import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.service.HomeLoanOfferingService;
import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeLoanOfferingController.class)
public class HomeLoanOfferingControllerTest {

    @MockBean
    private HomeLoanOfferingService homeLoanOfferingService;

    @MockBean
    private TokenManager tokenManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void whenGetAllLoanOfferings_thenReturnListOfOfferings() throws Exception {
        HomeLoanOffering offering1 = new HomeLoanOffering();
        HomeLoanOffering offering2 = new HomeLoanOffering();
        List<HomeLoanOffering> expectedOfferings = Arrays.asList(offering1, offering2);

        given(homeLoanOfferingService.findAllLoanOfferings()).willReturn(expectedOfferings);

        mockMvc.perform(get("/api/loan-offers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedOfferings.size()))
                .andExpect(jsonPath("$[0].id").value(offering1.getId()))
                .andExpect(jsonPath("$[1].id").value(offering2.getId()));
    }

    @Test
    @WithMockUser
    void whenGetLoanOfferingByIdAndOfferingExists_thenReturnOffering() throws Exception {
        Long offeringId = 1L;
        HomeLoanOffering offering = new HomeLoanOffering();
        offering.setId(offeringId);

        given(homeLoanOfferingService.findLoanOfferingById(offeringId)).willReturn(Optional.of(offering));
        mockMvc.perform(get("/api/loan-offers/{id}", offeringId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(offeringId));
    }

    @Test
    @WithMockUser
    void whenGetLoanOfferingByIdAndOfferingDoesNotExist_thenNotFound() throws Exception {
        Long invalidOfferingId = 99L;
        given(homeLoanOfferingService.findLoanOfferingById(invalidOfferingId)).willReturn(Optional.empty());
        mockMvc.perform(get("/api/loan-offers/{id}", invalidOfferingId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

