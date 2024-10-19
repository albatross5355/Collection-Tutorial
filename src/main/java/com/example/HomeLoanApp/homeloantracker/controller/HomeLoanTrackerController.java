package com.example.HomeLoanApp.homeloantracker.controller;

import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloanapplication.service.HomeLoanApplicationService;
import com.example.HomeLoanApp.homeloanofferings.dto.LoanDisplayDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/home-loan-tracker")
public class HomeLoanTrackerController {
    private final HomeLoanApplicationService loanApplicationService;

    @Autowired
    public HomeLoanTrackerController(HomeLoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/my-loans")
    public ResponseEntity<List<LoanDisplayDto>> getMyLoans(Principal principal) {
        List<HomeLoanApplication> loans = loanApplicationService.findAllLoansForUser(principal.getName());
        List<LoanDisplayDto> loanDisplayDtos = loans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(loanDisplayDtos);
    }

    private LoanDisplayDto convertToDto(HomeLoanApplication loan) {
        LoanDisplayDto dto = new LoanDisplayDto();
        dto.setLoanAccountNumber(loan.getLoanAccountNumber());
        dto.setCreationDate(loan.getCreationDate());
        dto.setLoanAmount(loan.getLoanAmount());
        dto.setMessage(loan.getCreationDate().isAfter(LocalDateTime.now().minusWeeks(2)) ?
                "Home loan creation will be completed after required approvals" : "");
        return dto;
    }
}

