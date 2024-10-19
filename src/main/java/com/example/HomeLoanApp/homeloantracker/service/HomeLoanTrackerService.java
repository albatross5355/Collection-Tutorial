package com.example.HomeLoanApp.homeloantracker.service;

import com.example.HomeLoanApp.homeloandashboard.modal.HomeLoan;
import com.example.HomeLoanApp.homeloandashboard.repository.CustomerRepository;
import com.example.HomeLoanApp.homeloandashboard.repository.HomeLoanRepository;
import com.example.HomeLoanApp.homeloantracker.modal.HomeLoanTracker;
import com.example.HomeLoanApp.homeloantracker.repository.HomeLoanTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeLoanTrackerService {
    private HomeLoanTrackerRepository homeLoanRepository;

    @Autowired
    public void homeLoanTrackerService(HomeLoanTrackerRepository homeLoanRepository) {
        this.homeLoanRepository = homeLoanRepository;
    }

    public List<HomeLoanTracker> getLoansByUserId(String userId) {
        return homeLoanRepository.findByUserId(userId);
    }
}

