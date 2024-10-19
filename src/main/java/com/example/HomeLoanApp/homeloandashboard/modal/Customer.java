package com.example.HomeLoanApp.homeloandashboard.modal;

import com.example.HomeLoanApp.homeloansecurity.modal.User;
import lombok.Data;

import javax.persistence.*;

import javax.persistence.Id;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
