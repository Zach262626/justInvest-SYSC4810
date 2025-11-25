package mylabs.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.junit.jupiter.api.Assertions.*;

public class Problem4Test {

    private static final String TEST_DB = "passwords_test.txt";

    @BeforeEach
    public void setUp() {
        Problem2c.setPasswordDatabase(TEST_DB);
        deletePasswordsFile();
    }

    @AfterEach
    public void tearDown() {
        deletePasswordsFile();
    }

    @Test
    public void testLoginWithValidCredentials() throws Exception {
        Problem2c.addUser("testuser", "Valid123!", "Client");
        assertTrue(Problem4ab.loginUserWithCredentials("testuser", "Valid123!"));
    }

    @Test
    public void testLoginWithInvalidPassword() throws Exception {
        Problem2c.addUser("testuser", "Valid123!", "Client");
        assertFalse(Problem4ab.loginUserWithCredentials("testuser", "Wrong123!"));
    }

    @Test
    public void testLoginWithNonexistentUser() throws Exception {
        assertFalse(Problem4ab.loginUserWithCredentials("nonexistent", "Any123!"));
    }

    @Test
    public void testLoginClientRole() throws Exception {
        Problem2c.addUser("client1", "Client123!", "Client");
        assertTrue(Problem4ab.loginUserWithCredentials("client1", "Client123!"));
    }

    @Test
    public void testLoginPremiumClientRole() throws Exception {
        Problem2c.addUser("premclient", "Premium123!", "Premium Client");
        assertTrue(Problem4ab.loginUserWithCredentials("premclient", "Premium123!"));
    }

    @Test
    public void testLoginFinancialAdvisorRole() throws Exception {
        Problem2c.addUser("advisor", "Advisor123!", "Financial Advisor");
        assertTrue(Problem4ab.loginUserWithCredentials("advisor", "Advisor123!"));
    }

    @Test
    public void testLoginFinancialPlannerRole() throws Exception {
        Problem2c.addUser("planner", "Planner123!", "Financial Planner");
        assertTrue(Problem4ab.loginUserWithCredentials("planner", "Planner123!"));
    }

    @Test
    public void testLoginMultipleUsers() throws Exception {
        Problem2c.addUser("user1", "User1Pass!", "Client");
        Problem2c.addUser("user2", "User2Pass!", "Premium Client");
        Problem2c.addUser("user3", "User3Pass!", "Financial Advisor");

        assertTrue(Problem4ab.loginUserWithCredentials("user1", "User1Pass!"));
        assertTrue(Problem4ab.loginUserWithCredentials("user2", "User2Pass!"));
        assertTrue(Problem4ab.loginUserWithCredentials("user3", "User3Pass!"));
    }

    @Test
    public void testLoginEmptyUsername() throws Exception {
        assertFalse(Problem4ab.loginUserWithCredentials("", "Any123!"));
    }

    @Test
    public void testLoginEmptyPassword() throws Exception {
        Problem2c.addUser("testuser", "Valid123!", "Client");
        assertFalse(Problem4ab.loginUserWithCredentials("testuser", ""));
    }

    @Test
    public void testLoginCaseSensitiveUsername() throws Exception {
        Problem2c.addUser("TestUser", "Valid123!", "Client");
        assertFalse(Problem4ab.loginUserWithCredentials("testuser", "Valid123!"));
    }

    @Test
    public void testLoginCaseSensitivePassword() throws Exception {
        Problem2c.addUser("testuser", "Valid123!", "Client");
        assertFalse(Problem4ab.loginUserWithCredentials("testuser", "valid123!"));
    }

    @Test
    public void testLoginWithSpecialCharPassword() throws Exception {
        Problem2c.addUser("special", "Pass!@#$%", "Client");
        assertTrue(Problem4ab.loginUserWithCredentials("special", "Pass!@#$%"));
    }

    @Test
    public void testLoginFailsWithPartialMatch() throws Exception {
        Problem2c.addUser("testuser", "Valid123!", "Client");
        assertFalse(Problem4ab.loginUserWithCredentials("testuse", "Valid123!"));
    }

    @Test
    public void testLoginConsistency() throws Exception {
        Problem2c.addUser("consistent", "Const123!", "Premium Client");
        assertTrue(Problem4ab.loginUserWithCredentials("consistent", "Const123!"));
        assertTrue(Problem4ab.loginUserWithCredentials("consistent", "Const123!"));
    }

    private void deletePasswordsFile() {
        try {
            Files.deleteIfExists(Paths.get(TEST_DB));
        } catch (Exception e) {
            // Ignore
        }
    }
}