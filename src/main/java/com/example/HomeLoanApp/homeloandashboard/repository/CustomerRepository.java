package com.example.HomeLoanApp.homeloandashboard.repository;
import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserUsername(String username);
}
