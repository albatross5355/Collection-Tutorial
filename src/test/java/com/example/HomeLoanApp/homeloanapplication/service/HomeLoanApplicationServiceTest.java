package com.example.HomeLoanApp.homeloanapplication.service;
import com.example.HomeLoanApp.homeloanapplication.modal.HomeLoanApplication;
import com.example.HomeLoanApp.homeloanapplication.repository.HomeLoanApplicationRepository;
import com.example.HomeLoanApp.homeloandashboard.modal.Customer;
import com.example.HomeLoanApp.homeloandashboard.repository.CustomerRepository;
import com.example.HomeLoanApp.homeloanofferings.dto.HomeLoanApplicationDto;
import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.repository.HomeLoanOfferingRepository;
import com.example.HomeLoanApp.homeloansecurity.modal.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class HomeLoanApplicationServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private HomeLoanOfferingRepository homeLoanOfferingRepository;

    @Mock
    private HomeLoanApplicationRepository homeLoanApplicationRepository;

    @InjectMocks
    private HomeLoanApplicationService homeLoanApplicationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testApplyForLoan() {
        HomeLoanApplicationDto applicationDto = new HomeLoanApplicationDto();
        applicationDto.setLoanOfferingId(1L);
        applicationDto.setLoanAmount(100000.0);
        applicationDto.setTenureYears(10);

        String username = "testUser";

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUser(new User());
        when(customerRepository.findByUserUsername(username)).thenReturn(Optional.of(customer));

        HomeLoanOffering loanOffering = new HomeLoanOffering();
        loanOffering.setId(1L);
        when(homeLoanOfferingRepository.findById(applicationDto.getLoanOfferingId())).thenReturn(Optional.of(loanOffering));

        HomeLoanApplication application = new HomeLoanApplication();
        when(homeLoanApplicationRepository.save(any(HomeLoanApplication.class))).thenReturn(application);

        HomeLoanApplication result = homeLoanApplicationService.applyForLoan(applicationDto, username);

        assertEquals(application, result);
        verify(customerRepository, times(1)).findByUserUsername(username);
        verify(homeLoanOfferingRepository, times(1)).findById(applicationDto.getLoanOfferingId());
        verify(homeLoanApplicationRepository, times(1)).save(any(HomeLoanApplication.class));
    }

    @Test
    public void testCalculateEmi() {
        double loanAmount = 100000.0;
        double interestRate = 5.0;
        int tenureYears = 10;
        double emi = homeLoanApplicationService.calculateEmi(loanAmount, interestRate, tenureYears);
        double expectedEmi = 1060.65;
        assertEquals(expectedEmi, emi, 0.01);
    }

    @Test
    public void testFindAllLoansForUser() {
        String username = "testUser";
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setUser(new User());
        when(customerRepository.findByUserUsername(username)).thenReturn(Optional.of(customer));
        HomeLoanApplication application = new HomeLoanApplication();
        when(homeLoanApplicationRepository.findByCustomer(customer)).thenReturn(Collections.singletonList(application));

        assertEquals(Collections.singletonList(application), homeLoanApplicationService.findAllLoansForUser(username));
        verify(customerRepository, times(1)).findByUserUsername(username);
        verify(homeLoanApplicationRepository, times(1)).findByCustomer(customer);
    }
}