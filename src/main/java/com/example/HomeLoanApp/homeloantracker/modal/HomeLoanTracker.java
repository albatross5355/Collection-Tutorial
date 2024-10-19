package com.example.HomeLoanApp.homeloantracker.modal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class HomeLoanTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loanAccountNumber;
    private Date creationDate;
    private double loanAmount;
    private String userId;
}
