package com.example.HomeLoanApp.homeloanofferings.modal;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.GenerationType;

@Entity
@Data
public class HomeLoanOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Double interestRate;

    public HomeLoanOffering() {}
}
