//package com.example.HomeLoanApp.homeloandashboard.controller;
//
//import com.example.HomeLoanApp.homeloandashboard.controlller.HomeLoanDashboardController;
//import com.example.HomeLoanApp.homeloandashboard.modal.HomeLoan;
//import com.example.HomeLoanApp.homeloandashboard.service.HomeLoanService;
//import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import static org.mockito.BDDMockito.given;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(HomeLoanDashboardController.class)
//public class HomeLoanDashboardControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private HomeLoanService homeLoanService;
//
//    @BeforeGroup
//
//
//    @MockBean
//    private TokenManager tokenManager;
//
//    @InjectMocks
//    private HomeLoanDashboardController homeLoanDashboardController;
//
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//
//    @Test
//    @WithMockUser(username = "user@example.com")
//    void whenGetMyHomeLoans_thenReturnListOfLoans() throws Exception {
//        HomeLoan loan1 = new HomeLoan();
//        HomeLoan loan2 = new HomeLoan();
//        List<HomeLoan> expectedLoans = Arrays.asList(loan1, loan2);
//        given(homeLoanService.findLoansByCustomerUsername("user@example.com")).willReturn(expectedLoans);
//        mockMvc.perform(get("/api/loans/user-dashboard")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(expectedLoans.size()));
//    }
//
//    @Test
//    @WithMockUser(username = "admin", authorities = {"ADMIN"})
//    void whenGetAllHomeLoansWithAdminAuthority_thenReturnListOfLoans() throws Exception {
//        HomeLoan loan1 = new HomeLoan();
//        HomeLoan loan2 = new HomeLoan();
//        List<HomeLoan> expectedLoans = Arrays.asList(loan1, loan2);
//        given(homeLoanService.findAllLoans()).willReturn(expectedLoans);
//        mockMvc.perform(get("/api/loans/admin-dashboard")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(expectedLoans.size()));
//    }
//
//    @Test
//    @WithMockUser(username = "user@example.com")
//    void whenGetLoanDetailsWithValidLoanId_thenReturnLoanDetails() throws Exception {
//        Long loanId = 1L;
//        HomeLoan loan = new HomeLoan();
//        given(homeLoanService.findLoanByIdAndUsername(loanId, "user@example.com")).willReturn(Optional.of(loan));
//        mockMvc.perform(get("/api/loans/{loanId}", loanId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser(username = "user@example.com")
//    void whenGetLoanDetailsWithInvalidLoanId_thenNotFound() throws Exception {
//        Long invalidLoanId = 99L;
//        given(homeLoanService.findLoanByIdAndUsername(invalidLoanId, "user@example.com")).willReturn(Optional.empty());
//        mockMvc.perform(get("/api/loans/{loanId}", invalidLoanId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//}
