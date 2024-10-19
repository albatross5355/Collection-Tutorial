package com.example.HomeLoanApp.homeloanofferings.service;

import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.repository.HomeLoanOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeLoanOfferingService {
    private final HomeLoanOfferingRepository homeLoanOfferingRepository;

    @Autowired
    public HomeLoanOfferingService(HomeLoanOfferingRepository homeLoanOfferingRepository) {
        this.homeLoanOfferingRepository = homeLoanOfferingRepository;
    }

    public List<HomeLoanOffering> findAllLoanOfferings() {
        return homeLoanOfferingRepository.findAll();
    }

    public Optional<HomeLoanOffering> findLoanOfferingById(Long id) {
        return homeLoanOfferingRepository.findById(id);
    }
}
