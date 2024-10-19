package com.example.HomeLoanApp.homeloandashboard.service;

import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import com.example.HomeLoanApp.homeloandashboard.modal.HomeLoan;
import com.example.HomeLoanApp.homeloandashboard.repository.CustomerRepository;
import com.example.HomeLoanApp.homeloandashboard.repository.HomeLoanRepository;
import com.example.HomeLoanApp.homeloansecurity.modal.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HomeLoanServiceTest {

    @Mock
    private HomeLoanRepository homeLoanRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private HomeLoanService homeLoanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindLoansByCustomerUsername() {
        String username = "testUser";

        List<HomeLoan> expectedLoans = Collections.singletonList(new HomeLoan());
        when(homeLoanRepository.findByCustomerUserUsername(username)).thenReturn(expectedLoans);

        List<HomeLoan> result = homeLoanService.findLoansByCustomerUsername(username);

        assertEquals(expectedLoans, result);
        verify(homeLoanRepository, times(1)).findByCustomerUserUsername(username);
    }

    @Test
    public void testFindAllLoans() {
        List<HomeLoan> expectedLoans = Collections.singletonList(new HomeLoan());
        when(homeLoanRepository.findAll()).thenReturn(expectedLoans);

        List<HomeLoan> result = homeLoanService.findAllLoans();

        assertEquals(expectedLoans, result);
        verify(homeLoanRepository, times(1)).findAll();
    }

    @Test
    public void testFindLoanByIdAndUsername() {
        Long loanId = 1L;
        String username = "testUser";

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUser(new User());
        when(customerRepository.findByUserUsername(username)).thenReturn(Optional.of(customer));

        HomeLoan expectedLoan = new HomeLoan();
        when(homeLoanRepository.findByIdAndCustomerId(loanId, customer.getId())).thenReturn(Optional.of(expectedLoan));

        Optional<HomeLoan> result = homeLoanService.findLoanByIdAndUsername(loanId, username);

        assertEquals(Optional.of(expectedLoan), result);
        verify(customerRepository, times(1)).findByUserUsername(username);
        verify(homeLoanRepository, times(1)).findByIdAndCustomerId(loanId, customer.getId());
    }
}

