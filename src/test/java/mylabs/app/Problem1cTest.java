package mylabs.app;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class Problem1cTest {
    private static final LocalTime BUSINESS_HOURS = LocalTime.of(12, 0);

    @Test
    public void testClientOperations() {
        assertTrue(Problem1c.validateOperationAccess("Client", "1", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Client", "2", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Client", "4", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("Client", "3", BUSINESS_HOURS));
    }

    @Test
    public void testPremiumClientOperations() {
        assertTrue(Problem1c.validateOperationAccess("Premium Client", "3", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Premium Client", "5", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("Premium Client", "6", BUSINESS_HOURS));
    }

    @Test
    public void testFinancialAdvisorOperations() {
        assertTrue(Problem1c.validateOperationAccess("Financial Advisor", "3", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Financial Advisor", "7", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("Financial Advisor", "6", BUSINESS_HOURS));
    }

    @Test
    public void testFinancialPlannerOperations() {
        assertTrue(Problem1c.validateOperationAccess("Financial Planner", "6", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Financial Planner", "3", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Financial Planner", "7", BUSINESS_HOURS));
    }

    @Test
    public void testTellerOperations() {
        assertTrue(Problem1c.validateOperationAccess("Teller", "1", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("Teller", "2", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("Teller", "3", BUSINESS_HOURS));
    }

    @Test
    public void testTellerTimeRestrictions() {
        assertFalse(Problem1c.validateOperationAccess("Teller", "1", LocalTime.of(8, 59)));
        assertTrue(Problem1c.validateOperationAccess("Teller", "1", LocalTime.of(9, 0)));
        assertTrue(Problem1c.validateOperationAccess("Teller", "1", LocalTime.of(16, 59)));
        assertFalse(Problem1c.validateOperationAccess("Teller", "1", LocalTime.of(17, 1)));
    }

    @Test
    public void testInvalidRole() {
        assertFalse(Problem1c.validateOperationAccess("INVALID", "1", BUSINESS_HOURS));
    }
}
