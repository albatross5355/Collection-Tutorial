package com.example.HomeLoanApp.homeloanapplication.service;

import javax.persistence.EntityNotFoundException;

import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloanapplication.repository.HomeLoanApplicationRepository;
import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import com.example.HomeLoanApp.homeloandashboard.repository.CustomerRepository;
import com.example.HomeLoanApp.homeloanofferings.dto.HomeLoanApplicationDto;
import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.repository.HomeLoanOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class HomeLoanApplicationService {
    private final CustomerRepository customerRepository;
    private final HomeLoanOfferingRepository homeLoanOfferingRepository;
    private final HomeLoanApplicationRepository homeLoanApplicationRepository;

    @Autowired
    public HomeLoanApplicationService(CustomerRepository customerRepository, HomeLoanOfferingRepository homeLoanOfferingRepository, HomeLoanApplicationRepository homeLoanApplicationRepository) {
        this.customerRepository = customerRepository;
        this.homeLoanOfferingRepository = homeLoanOfferingRepository;
        this.homeLoanApplicationRepository = homeLoanApplicationRepository;
    }

    public HomeLoanApplication applyForLoan(HomeLoanApplicationDto applicationDto, String username) {
        Customer customer = customerRepository.findByUserUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        HomeLoanOffering loanOffering = homeLoanOfferingRepository.findById(applicationDto.getLoanOfferingId())
                .orElseThrow(() -> new EntityNotFoundException("Loan offering not found"));

        HomeLoanApplication application = new HomeLoanApplication();
        application.setCustomer(customer);
        application.setLoanOffering(loanOffering);
        application.setLoanAmount(applicationDto.getLoanAmount());
        application.setTenureYears(applicationDto.getTenureYears());

        return homeLoanApplicationRepository.save(application);
    }

    public Double calculateEmi(Double loanAmount, Double interestRate, Integer tenureYears) {
        double monthlyInterestRate = interestRate / 12 / 100;
        double tenureMonths = tenureYears * 12;
        double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, tenureMonths)) /
                (Math.pow(1 + monthlyInterestRate, tenureMonths) - 1);
        return emi;
    }

    public List<HomeLoanApplication> findAllLoansForUser(String username) {
        return customerRepository.findByUserUsername(username)
                .map(customer -> homeLoanApplicationRepository.findByCustomer(customer))
                .orElse(Collections.emptyList());
    }
}