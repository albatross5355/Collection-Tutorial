package com.example.HomeLoanApp.homeloandashboard.modal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HomeLoanTest {

    @Test
    void testEqualsAndHashCode() {
        // Given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("a");
        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setName("a");


        HomeLoan loan1 = new HomeLoan();
        loan1.setId(1L);
        loan1.setLoanType("Type1");
        loan1.setTotalLoanAmount(1000.0);
        loan1.setLoanTenure(10);
        loan1.setCurrentRateOfInterest(5.0);
        loan1.setPrincipalOutstandingAmount(800.0);
        loan1.setOutstandingEMICount(5);
        loan1.setCustomer(customer1);

        HomeLoan loan1Copy = new HomeLoan();
        loan1Copy.setId(1L);
        loan1Copy.setLoanType("Type1");
        loan1Copy.setTotalLoanAmount(1000.0);
        loan1Copy.setLoanTenure(10);
        loan1Copy.setCurrentRateOfInterest(5.0);
        loan1Copy.setPrincipalOutstandingAmount(800.0);
        loan1Copy.setOutstandingEMICount(5);
        loan1Copy.setCustomer(customer1);

        HomeLoan loan2 = new HomeLoan();
        loan2.setId(2L);
        loan2.setLoanType("Type2");
        loan2.setTotalLoanAmount(2000.0);
        loan2.setLoanTenure(20);
        loan2.setCurrentRateOfInterest(6.0);
        loan2.setPrincipalOutstandingAmount(1600.0);
        loan2.setOutstandingEMICount(10);
        loan2.setCustomer(customer2);
        assertEquals(loan1, loan1);
        assertEquals(loan1, loan1Copy);
        assertEquals(loan1.hashCode(), loan1Copy.hashCode());
        assertNotEquals(loan1, loan2);
        assertNotEquals(loan1.hashCode(), loan2.hashCode());
        assertFalse(loan1.equals(null));
    }

    @Test
    void testToString() {
        Customer customer = new Customer();
        customer.setId(1L);
        HomeLoan loan = new HomeLoan();
        loan.setId(1L);
        loan.setLoanType("Type1");
        loan.setTotalLoanAmount(1000.0);
        loan.setLoanTenure(10);
        loan.setCurrentRateOfInterest(5.0);
        loan.setPrincipalOutstandingAmount(800.0);
        loan.setOutstandingEMICount(5);
        loan.setCustomer(customer);
        String expectedToString = "HomeLoan(id=1, loanType=Type1, totalLoanAmount=1000.0, loanTenure=10, currentRateOfInterest=5.0, principalOutstandingAmount=800.0, outstandingEMICount=5, customer=Customer(id=1, name=null, user=null))";
        assertEquals(expectedToString, loan.toString());
    }
}

