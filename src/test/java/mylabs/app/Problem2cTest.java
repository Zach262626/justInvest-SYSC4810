package mylabs.app;   
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class Problem2cTest {
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
    public void testAddUserCreatesEntry() throws Exception {
        Problem2c.addUser("alice", "Pass123!", "Client");
        List<String> userInfo = Problem2c.retrieveUserInfo("alice");
        assertNotNull(userInfo);
        assertEquals(userInfo.size(), 3);
    }

    @Test
    public void testVerifyCorrectPassword() throws Exception {
        Problem2c.addUser("bob", "Pass456@", "Premium Client");
        assertTrue(Problem2c.verifyUser("bob", "Pass456@"));
    }

    @Test
    public void testRejectWrongPassword() throws Exception {
        Problem2c.addUser("charlie", "Pass789#", "Financial Advisor");
        assertFalse(Problem2c.verifyUser("charlie", "WrongPass123!"));
    }

    @Test
    public void testNonexistentUserReturnsNull() throws Exception {
        List<String> userInfo = Problem2c.retrieveUserInfo("nobody");
        assertNull(userInfo);
    }

    @Test
    public void testMultipleUsersCanBeStored() throws Exception {
        Problem2c.addUser("user1", "Pass111!", "Client");
        Problem2c.addUser("user2", "Pass222@", "Premium Client");
        Problem2c.addUser("user3", "Pass333#", "Financial Advisor");

        assertNotNull(Problem2c.retrieveUserInfo("user1"));
        assertNotNull(Problem2c.retrieveUserInfo("user2"));
        assertNotNull(Problem2c.retrieveUserInfo("user3"));
    }

    @Test
    public void testDifferentSaltsGenerated() throws Exception {
        Problem2c.addUser("salttest1", "SamePass!", "Teller");
        Problem2c.addUser("salttest2", "SamePass!", "Client");

        List<String> user1Info = Problem2c.retrieveUserInfo("salttest1");
        List<String> user2Info = Problem2c.retrieveUserInfo("salttest2");

        assertNotEquals(user1Info.get(0), user2Info.get(0));
    }

    @Test
    public void testFileFormatIsCorrect() throws Exception {
        Problem2c.addUser("format", "Pass999$", "Financial Planner");

        String line = Files.readAllLines(Paths.get(TEST_DB)).get(0);
        long pipeCount = line.chars().filter(ch -> ch == '|').count();

        assertEquals(pipeCount, 3);
    }

    @Test
    public void testSpecialCharactersInPassword() throws Exception {
        Problem2c.addUser("special", "Pass!@#$%", "Client");
        assertTrue(Problem2c.verifyUser("special", "Pass!@#$%"));
    }

    @Test
    public void testPasswordVerificationIsConsistent() throws Exception {
        Problem2c.addUser("consistent", "Pass111!", "Premium Client");
        boolean result1 = Problem2c.verifyUser("consistent", "Pass111!");
        boolean result2 = Problem2c.verifyUser("consistent", "Pass111!");

        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testRetrieveUserInfoReturnsThreeComponents() throws Exception {
        Problem2c.addUser("getinfo", "Pass222@", "Financial Advisor");
        List<String> info = Problem2c.retrieveUserInfo("getinfo");

        assertNotNull(info);
        assertEquals(info.size(), 3);
    }

    private void deletePasswordsFile() {
        try {
            Files.deleteIfExists(Paths.get(TEST_DB));
        } catch (Exception e) {
            //
        }
    }
}