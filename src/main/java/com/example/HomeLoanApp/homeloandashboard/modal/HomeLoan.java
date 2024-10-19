package com.example.HomeLoanApp.homeloandashboard.modal;

import lombok.Data;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class HomeLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loanType;
    private Double totalLoanAmount;
    private Integer loanTenure;
    private Double currentRateOfInterest;
    private Double principalOutstandingAmount;
    private Integer outstandingEMICount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}

