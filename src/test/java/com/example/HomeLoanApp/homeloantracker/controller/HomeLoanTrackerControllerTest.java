package com.example.HomeLoanApp.homeloantracker.controller;

import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloanapplication.service.HomeLoanApplicationService;
import com.example.HomeLoanApp.homeloanofferings.dto.LoanDisplayDto;
import com.example.HomeLoanApp.homeloansecurity.auth.TokenManager;
import com.example.HomeLoanApp.homeloantracker.controller.HomeLoanTrackerController;
import com.example.HomeLoanApp.homeloantracker.service.HomeLoanTrackerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeLoanTrackerController.class)
public class HomeLoanTrackerControllerTest {

    @MockBean
    private HomeLoanApplicationService loanTrackerService;

    @MockBean
    private TokenManager tokenManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user@example.com")
    void whenGetMyLoans_thenReturnListOfLoanDisplayDtos() throws Exception {
        LoanDisplayDto dto1 = new LoanDisplayDto();
        dto1.setLoanAccountNumber("123456789");
        dto1.setCreationDate(LocalDateTime.now().minusDays(5));
        dto1.setLoanAmount(100000.00);
        dto1.setMessage("Loan application in process");

        LoanDisplayDto dto2 = new LoanDisplayDto();
        dto2.setLoanAccountNumber("987654321");
        dto2.setCreationDate(LocalDateTime.now().minusDays(10));
        dto2.setLoanAmount(200000.00);
        dto2.setMessage("Loan application approved");

        List<LoanDisplayDto> expectedDtos = Arrays.asList(dto1, dto2);

        List<HomeLoanApplication> loans = expectedDtos.stream()
                .map(dto -> {
                    HomeLoanApplication loan = new HomeLoanApplication();
                    loan.setLoanAccountNumber(dto.getLoanAccountNumber());
                    loan.setCreationDate(dto.getCreationDate());
                    loan.setLoanAmount(dto.getLoanAmount());
                    return loan;
                })
                .collect(Collectors.toList());
        given(loanTrackerService.findAllLoansForUser(anyString())).willReturn(loans);
        mockMvc.perform(get("/api/home-loan-tracker/my-loans")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedDtos.size()))
                .andExpect(jsonPath("$[0].loanAccountNumber").value(dto1.getLoanAccountNumber()))
                .andExpect(jsonPath("$[1].loanAccountNumber").value(dto2.getLoanAccountNumber()))
                .andExpect(jsonPath("$[0].loanAmount").value(dto1.getLoanAmount()))
                .andExpect(jsonPath("$[1].loanAmount").value(dto2.getLoanAmount()));
    }
}

