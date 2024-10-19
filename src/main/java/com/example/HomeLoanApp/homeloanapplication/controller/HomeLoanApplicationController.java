package com.example.HomeLoanApp.homeloanapplication.controller;

import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloanapplication.service.HomeLoanApplicationService;
import com.example.HomeLoanApp.homeloanofferings.dto.HomeLoanApplicationDto;
import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;

import com.example.HomeLoanApp.homeloanofferings.service.HomeLoanOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/home-loans")
public class HomeLoanApplicationController {
    @Autowired
    private  HomeLoanOfferingService loanOfferingService;
    @Autowired
    private  HomeLoanApplicationService loanApplicationService;


    public HomeLoanApplicationController(HomeLoanOfferingService loanOfferingService, HomeLoanApplicationService loanApplicationService) {
        this.loanOfferingService = loanOfferingService;
        this.loanApplicationService = loanApplicationService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<HomeLoanOffering>> getAllLoanProducts() {
        List<HomeLoanOffering> products = loanOfferingService.findAllLoanOfferings();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/apply")
    public ResponseEntity<HomeLoanApplication> applyForLoan(@RequestBody HomeLoanApplicationDto applicationDto, Principal principal) {
        HomeLoanApplication application = loanApplicationService.applyForLoan(applicationDto, principal.getName());
        return new ResponseEntity<>(application, HttpStatus.CREATED);
    }

    @GetMapping("/calculate-emi")
    public ResponseEntity<Double> calculateEmi(@RequestParam Double loanAmount, @RequestParam Double interestRate, @RequestParam Integer tenureYears) {
        Double emi = loanApplicationService.calculateEmi(loanAmount, interestRate, tenureYears);
        return ResponseEntity.ok(emi);
    }
}
