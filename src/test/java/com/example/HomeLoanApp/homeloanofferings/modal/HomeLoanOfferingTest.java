package com.example.HomeLoanApp.homeloanofferings.modal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeLoanOfferingTest {

    @Test
    void testEqualsAndHashCode() {
        // Given
        HomeLoanOffering offering1 = new HomeLoanOffering();
        offering1.setId(1L);
        offering1.setName("Offering1");
        offering1.setDescription("Description1");
        offering1.setInterestRate(5.0);

        HomeLoanOffering offering1Copy = new HomeLoanOffering();
        offering1Copy.setId(1L);
        offering1Copy.setName("Offering1");
        offering1Copy.setDescription("Description1");
        offering1Copy.setInterestRate(5.0);

        HomeLoanOffering offering2 = new HomeLoanOffering();
        offering2.setId(2L);
        offering2.setName("Offering2");
        offering2.setDescription("Description2");
        offering2.setInterestRate(6.0);

        // When and Then
        assertEquals(offering1, offering1); // Reflexive
        assertEquals(offering1, offering1Copy); // Symmetric
        assertEquals(offering1.hashCode(), offering1Copy.hashCode());
        assertNotEquals(offering1, offering2); // Different objects
        assertNotEquals(offering1.hashCode(), offering2.hashCode());

        // Ensure equals handles null
        assertFalse(offering1.equals(null));
    }

    @Test
    void testToString() {
        // Given
        HomeLoanOffering offering = new HomeLoanOffering();
        offering.setId(1L);
        offering.setName("Offering1");
        offering.setDescription("Description1");
        offering.setInterestRate(5.0);

        // When
        String expectedToString = "HomeLoanOffering(id=1, name=Offering1, description=Description1, interestRate=5.0)";

        // Then
        assertEquals(expectedToString, offering.toString());
    }
}
