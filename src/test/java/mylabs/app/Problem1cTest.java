package mylabs.app;

import org.junit.jupiter.api.Test;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

public class Problem1cTest {
    private static final LocalTime BUSINESS_HOURS = LocalTime.of(12, 0);

    @Test
    public void testClientOperations() {
        assertTrue(Problem1c.validateOperationAccess("C", "1", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("C", "2", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("C", "4", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("C", "3", BUSINESS_HOURS));
    }

    @Test
    public void testPremiumClientOperations() {
        assertTrue(Problem1c.validateOperationAccess("PC", "3", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("PC", "5", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("PC", "6", BUSINESS_HOURS));
    }

    @Test
    public void testFinancialAdvisorOperations() {
        assertTrue(Problem1c.validateOperationAccess("FA", "3", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("FA", "7", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("FA", "6", BUSINESS_HOURS));
    }

    @Test
    public void testFinancialPlannerOperations() {
        assertTrue(Problem1c.validateOperationAccess("FP", "6", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("FP", "3", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("FP", "7", BUSINESS_HOURS));
    }

    @Test
    public void testTellerOperations() {
        assertTrue(Problem1c.validateOperationAccess("T", "1", BUSINESS_HOURS));
        assertTrue(Problem1c.validateOperationAccess("T", "2", BUSINESS_HOURS));
        assertFalse(Problem1c.validateOperationAccess("T", "3", BUSINESS_HOURS));
    }

    @Test
    public void testTellerTimeRestrictions() {
        assertFalse(Problem1c.validateOperationAccess("T", "1", LocalTime.of(8, 59)));
        assertTrue(Problem1c.validateOperationAccess("T", "1", LocalTime.of(9, 0)));
        assertTrue(Problem1c.validateOperationAccess("T", "1", LocalTime.of(16, 59)));
        assertFalse(Problem1c.validateOperationAccess("T", "1", LocalTime.of(17, 1)));
    }

    @Test
    public void testInvalidRole() {
        assertFalse(Problem1c.validateOperationAccess("INVALID", "1", BUSINESS_HOURS));
    }
}
