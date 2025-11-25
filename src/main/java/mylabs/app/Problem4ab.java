//Zachary Gallant 101272210
package mylabs.app;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


public class Problem4ab {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");


    public static boolean loginUserWithCredentials(String attemptedUsername, String attemptedPassword) {
        return authenticateAndAuthorize(attemptedUsername, attemptedPassword);
    }


    private static boolean authenticateAndAuthorize(String username, String password) {
        try {
            LocalTime accessTime = LocalTime.now();
            List<String> accountData = Problem2c.retrieveUserInfo(username);
            if (accountData == null) {
                System.out.println("Account does not exist");
                return false;
            }

            boolean credentialsValid = Problem2c.verifyUser(username, password);
            if (!credentialsValid) {
                System.out.println("Invalid credentials");
                return false;
            }

            String userRole = accountData.get(2);

            if (userRole.equals("Teller")) {
                LocalTime businessStart = LocalTime.of(9, 0);
                LocalTime businessEnd = LocalTime.of(17, 0);
                boolean withinHours = !accessTime.isBefore(businessStart) && !accessTime.isAfter(businessEnd);

                if (!withinHours) {
                    return false;
                }
            }

            displayAuthorizationInfo(username, userRole);
            return true;

        } catch (Exception e) {
            System.out.println("Authentication error: " + e.getMessage());
            return false;
        }
    }


    private static void displayAuthorizationInfo(String username, String role) throws IOException {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LOGIN SUCCESSFUL");
        System.out.println("=".repeat(50));
        System.out.println("\nUser Information:");
        System.out.println("  Username: " + username);
        System.out.println("  Role: " + role);
        System.out.println("\nAuthorized Permissions:");

        Problem1c.showPermissions(role);

        System.out.println("=".repeat(50) + "\n");
    }
}