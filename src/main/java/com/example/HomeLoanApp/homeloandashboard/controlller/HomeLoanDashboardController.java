package com.example.HomeLoanApp.homeloandashboard.controlller;

import com.example.HomeLoanApp.homeloandashboard.modal.HomeLoan;
import com.example.HomeLoanApp.homeloandashboard.service.HomeLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class HomeLoanDashboardController {

    @Autowired
    private  HomeLoanService homeLoanService;


    @GetMapping("/user-dashboard")
    public ResponseEntity<List<HomeLoan>> getMyHomeLoans(Principal principal) {
        List<HomeLoan> loans = homeLoanService.findLoansByCustomerUsername(principal.getName());
        return ResponseEntity.ok(loans);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin-dashboard")
    public ResponseEntity<List<HomeLoan>> getAllHomeLoans() {
        List<HomeLoan> loans = homeLoanService.findAllLoans();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<HomeLoan> getLoanDetails(@PathVariable Long loanId, Principal principal) {
        return homeLoanService.findLoanByIdAndUsername(loanId, principal.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


