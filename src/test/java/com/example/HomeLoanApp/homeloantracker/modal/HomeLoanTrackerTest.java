package com.example.HomeLoanApp.homeloantracker.modal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class HomeLoanTrackerTest {

    @Test
    void testEqualsAndHashCode() {
        // Given
        HomeLoanTracker tracker1 = new HomeLoanTracker();
        tracker1.setId(1L);
        tracker1.setLoanAccountNumber("ACC123");
        tracker1.setCreationDate(new Date(1234567890));
        tracker1.setLoanAmount(10000.0);
        tracker1.setUserId("user1");

        HomeLoanTracker tracker1Copy = new HomeLoanTracker();
        tracker1Copy.setId(1L);
        tracker1Copy.setLoanAccountNumber("ACC123");
        tracker1Copy.setCreationDate(new Date(1234567890));
        tracker1Copy.setLoanAmount(10000.0);
        tracker1Copy.setUserId("user1");

        HomeLoanTracker tracker2 = new HomeLoanTracker();
        tracker2.setId(2L);
        tracker2.setLoanAccountNumber("ACC456");
        tracker2.setCreationDate(new Date(9876543210L));
        tracker2.setLoanAmount(20000.0);
        tracker2.setUserId("user2");
        assertEquals(tracker1, tracker1);
        assertEquals(tracker1, tracker1Copy);
        assertEquals(tracker1.hashCode(), tracker1Copy.hashCode());
        assertNotEquals(tracker1, tracker2);
        assertNotEquals(tracker1.hashCode(), tracker2.hashCode());
        assertFalse(tracker1.equals(null));
    }

    @Test
    void testToString() {
        // Given
        HomeLoanTracker tracker = new HomeLoanTracker();
        tracker.setId(1L);
        tracker.setLoanAccountNumber("ACC123");
        tracker.setCreationDate(new Date(1234567890));
        tracker.setLoanAmount(10000.0);
        tracker.setUserId("user1");
        String expectedToString = "HomeLoanTracker(id=1, loanAccountNumber=ACC123, creationDate=Thu Jan 15 12:26:07 IST 1970, loanAmount=10000.0, userId=user1)";
        assertEquals(expectedToString, tracker.toString());
    }
}