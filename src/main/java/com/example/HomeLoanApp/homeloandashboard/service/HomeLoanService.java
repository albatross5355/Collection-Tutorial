package com.example.HomeLoanApp.homeloandashboard.service;

import com.example.HomeLoanApp.homeloandashboard.modal.HomeLoan;
import com.example.HomeLoanApp.homeloandashboard.repository.CustomerRepository;
import com.example.HomeLoanApp.homeloandashboard.repository.HomeLoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeLoanService {
    private final HomeLoanRepository homeLoanRepository;

    private final CustomerRepository customerRepository;

    public HomeLoanService(HomeLoanRepository homeLoanRepository, CustomerRepository customerRepository) {
        this.homeLoanRepository = homeLoanRepository;
        this.customerRepository = customerRepository;
    }

    public List<HomeLoan> findLoansByCustomerUsername(String username) {
        return homeLoanRepository.findByCustomerUserUsername(username);
    }

    public List<HomeLoan> findAllLoans() {
        return homeLoanRepository.findAll();
    }

    public Optional<HomeLoan> findLoanByIdAndUsername(Long loanId, String username) {
        return customerRepository.findByUserUsername(username)
                .flatMap(customer -> homeLoanRepository.findByIdAndCustomerId(loanId, customer.getId()));
    }
}