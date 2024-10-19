package com.example.HomeLoanApp.homeloantracker.service;
import com.example.HomeLoanApp.homeloantracker.modal.HomeLoanTracker;
import com.example.HomeLoanApp.homeloantracker.repository.HomeLoanTrackerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class HomeLoanTrackerServiceTest {

    @Mock
    private HomeLoanTrackerRepository homeLoanTrackerRepository;

    @InjectMocks
    private HomeLoanTrackerService homeLoanTrackerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLoansByUserId() {
        String userId = "testUserId";
        List<HomeLoanTracker> expectedLoans = Collections.singletonList(new HomeLoanTracker());
        when(homeLoanTrackerRepository.findByUserId(userId)).thenReturn(expectedLoans);
        List<HomeLoanTracker> result = homeLoanTrackerService.getLoansByUserId(userId);
        assertEquals(expectedLoans, result);
        verify(homeLoanTrackerRepository, times(1)).findByUserId(userId);
    }
}
