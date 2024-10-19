package com.example.HomeLoanApp.homeloanofferings.service;

import com.example.HomeLoanApp.homeloanofferings.modal.HomeLoanOffering;
import com.example.HomeLoanApp.homeloanofferings.repository.HomeLoanOfferingRepository;
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

public class HomeLoanOfferingServiceTest {

    @Mock
    private HomeLoanOfferingRepository homeLoanOfferingRepository;

    @InjectMocks
    private HomeLoanOfferingService homeLoanOfferingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllLoanOfferings() {
        List<HomeLoanOffering> expectedLoanOfferings = Collections.singletonList(new HomeLoanOffering());
        when(homeLoanOfferingRepository.findAll()).thenReturn(expectedLoanOfferings);
        List<HomeLoanOffering> result = homeLoanOfferingService.findAllLoanOfferings();
        assertEquals(expectedLoanOfferings, result);
        verify(homeLoanOfferingRepository, times(1)).findAll();
    }

    @Test
    public void testFindLoanOfferingById() {
        Long id = 1L;
        HomeLoanOffering expectedLoanOffering = new HomeLoanOffering();
        when(homeLoanOfferingRepository.findById(id)).thenReturn(Optional.of(expectedLoanOffering));
        Optional<HomeLoanOffering> result = homeLoanOfferingService.findLoanOfferingById(id);
        assertEquals(Optional.of(expectedLoanOffering), result);
        verify(homeLoanOfferingRepository, times(1)).findById(id);
    }
}
