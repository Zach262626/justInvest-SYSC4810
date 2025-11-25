package mylabs.app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Problem3Test {

    @Test
    public void testValidPasswordWithAllRequirements() {
        assertTrue(Problem3b.isValidPassword("Secure1!", "testuser"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(Problem3b.isValidPassword("Short1!", "testuser"));
    }

    @Test
    public void testPasswordTooLong() {
        assertFalse(Problem3b.isValidPassword("VeryLongPassword123!", "testuser"));
    }

    @Test
    public void testPasswordMissingUppercase() {
        assertFalse(Problem3b.isValidPassword("lowercase1!", "testuser"));
    }

    @Test
    public void testPasswordMissingLowercase() {
        assertFalse(Problem3b.isValidPassword("UPPERCASE1!", "testuser"));
    }

    @Test
    public void testPasswordMissingDigit() {
        assertFalse(Problem3b.isValidPassword("NoDigit!Pass", "testuser"));
    }

    @Test
    public void testPasswordMissingSpecialChar() {
        assertFalse(Problem3b.isValidPassword("NoSpecial123", "testuser"));
    }

    @Test
    public void testPasswordMatchesUsername() {
        assertFalse(Problem3b.isValidPassword("Testuser123!", "Testuser123!"));
    }

    @Test
    public void testWeakPasswordRejected() {
        assertFalse(Problem3b.isValidPassword("password", "testuser"));
    }

    @Test
    public void testCommonWeakPassword() {
        assertFalse(Problem3b.isValidPassword("123456", "testuser"));
    }

    @Test
    public void testSpecialCharsExclamation() {
        assertTrue(Problem3b.isValidPassword("Test123!", "testuser"));
    }

    @Test
    public void testSpecialCharsAtSymbol() {
        assertTrue(Problem3b.isValidPassword("Test1@user", "testuser"));
    }

    @Test
    public void testSpecialCharsHash() {
        assertTrue(Problem3b.isValidPassword("Test1#user", "testuser"));
    }

    @Test
    public void testSpecialCharsDollar() {
        assertTrue(Problem3b.isValidPassword("Test1$user", "testuser"));
    }

    @Test
    public void testSpecialCharsPercent() {
        assertTrue(Problem3b.isValidPassword("Test1%user", "testuser"));
    }

    @Test
    public void testSpecialCharsAsterisk() {
        assertTrue(Problem3b.isValidPassword("Test1*user", "testuser"));
    }

    @Test
    public void testSpecialCharsAmpersand() {
        assertTrue(Problem3b.isValidPassword("Test1&user", "testuser"));
    }

    @Test
    public void testRoleClientIsValid() {
        assertTrue(Problem3a.checkRole("Client"));
    }

    @Test
    public void testRolePremiumClientIsValid() {
        assertTrue(Problem3a.checkRole("Premium Client"));
    }

    @Test
    public void testRoleFinancialAdvisorIsValid() {
        assertTrue(Problem3a.checkRole("Financial Advisor"));
    }

    @Test
    public void testRoleFinancialPlannerIsValid() {
        assertTrue(Problem3a.checkRole("Financial Planner"));
    }

    @Test
    public void testRoleTellerIsValid() {
        assertTrue(Problem3a.checkRole("Teller"));
    }

    @Test
    public void testInvalidRoleRejected() {
        assertFalse(Problem3a.checkRole("InvalidRole"));
    }

    @Test
    public void testEmptyRoleRejected() {
        assertFalse(Problem3a.checkRole(""));
    }

    @Test
    public void testPasswordWithMultipleSpecialChars() {
        assertTrue(Problem3b.isValidPassword("Pass1!@#", "testuser"));
    }

    @Test
    public void testPasswordWithMaxLength() {
        assertTrue(Problem3b.isValidPassword("MaxPass123!", "testuser"));
    }

    @Test
    public void testPasswordWithMinLength() {
        assertTrue(Problem3b.isValidPassword("Min1Pass!", "testuser"));
    }
}