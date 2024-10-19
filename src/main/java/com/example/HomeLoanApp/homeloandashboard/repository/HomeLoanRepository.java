package com.example.HomeLoanApp.homeloandashboard.repository;

import com.example.HomeLoanApp.homeloandashboard.modal.HomeLoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HomeLoanRepository extends JpaRepository<HomeLoan, Long> {
    List<HomeLoan> findByCustomerUserUsername(String username);

    Optional<HomeLoan> findByIdAndCustomerId(Long loanId, Long id);
}
