package com.example.HomeLoanApp.homeloanapplication.repository;

import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeLoanApplicationRepository extends JpaRepository<HomeLoanApplication, Long> {
    List<HomeLoanApplication> findByCustomer(Customer customer);
}
