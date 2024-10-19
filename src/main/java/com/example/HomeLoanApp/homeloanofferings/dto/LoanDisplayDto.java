package com.example.HomeLoanApp.homeloanofferings.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LoanDisplayDto {
    private String loanAccountNumber;
    private LocalDateTime creationDate;
    private Double loanAmount;
    private String message;
}
