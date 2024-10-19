package com.example.HomeLoanApp.homeloantracker.repository;
import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import com.example.HomeLoanApp.homeloantracker.modal.HomeLoanTracker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface HomeLoanTrackerRepository extends JpaRepository<HomeLoanTracker, Long> {
    List<HomeLoanTracker> findByUserId(String userId);
}
