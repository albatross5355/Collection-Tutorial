package com.example.HomeLoanApp.homeloanofferings.controller;

import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.service.HomeLoanOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loan-offers")
public class HomeLoanOfferingController {

    @Autowired
    private  HomeLoanOfferingService homeLoanOfferingService;

    @Autowired
    public HomeLoanOfferingController(HomeLoanOfferingService homeLoanOfferingService) {
        this.homeLoanOfferingService = homeLoanOfferingService;
    }

    @GetMapping()
    public ResponseEntity<List<HomeLoanOffering>> getAllLoanOfferings() {
        List<HomeLoanOffering> offerings = homeLoanOfferingService.findAllLoanOfferings();
        return ResponseEntity.ok(offerings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HomeLoanOffering> getLoanOfferingById(@PathVariable Long id) {
        Optional<HomeLoanOffering> offering = homeLoanOfferingService.findLoanOfferingById(id);
        return offering
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
