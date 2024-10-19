package com.example.HomeLoanApp.homeloanofferings.dto;

import lombok.Data;

@Data
public class HomeLoanApplicationDto {
    private Long loanOfferingId;
    private Double loanAmount;
    private Integer tenureYears;
}
