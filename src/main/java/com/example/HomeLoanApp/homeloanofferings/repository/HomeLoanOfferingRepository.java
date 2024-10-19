package com.example.HomeLoanApp.homeloanofferings.repository;

import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeLoanOfferingRepository extends JpaRepository<HomeLoanOffering, Long> {
}
