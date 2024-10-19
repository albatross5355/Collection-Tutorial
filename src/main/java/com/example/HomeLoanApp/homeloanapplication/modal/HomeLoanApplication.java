package com.example.HomeLoanApp.homeloanapplication.modal;

import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
public class HomeLoanApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "loan_offering_id", nullable = false)
    private HomeLoanOffering loanOffering;

    private Double loanAmount;
    private Integer tenureYears;

    private String loanAccountNumber;
    private LocalDateTime creationDate;

}
