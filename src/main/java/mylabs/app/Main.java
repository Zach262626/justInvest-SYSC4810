//Zachary Gallant 101272210
package mylabs.app;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;


public class Main {

    private static final Scanner INPUT = new Scanner(System.in);
    private static final String EXIT_COMMAND = "E";


    public static void main(String[] args) {
        displayWelcome();
        authenticationLoop();
    }

    private static void displayWelcome() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("justInvest System");
        System.out.println("=".repeat(50));
        System.out.println("\nAvailable Operations:");
        System.out.println("  1. View account balance");
        System.out.println("  2. View investment portfolio");
        System.out.println("  3. Modify investment portfolio");
        System.out.println("  4. View financial advisor contact info");
        System.out.println("  5. View financial planner contact info");
        System.out.println("  6. View money market instruments");
        System.out.println("  7. View private consumer instruments");
        System.out.println("=".repeat(50) + "\n");
    }


    private static void authenticationLoop() {
        while (true) {
            System.out.print("Username: ");
            String username = INPUT.nextLine().trim();

            if (username.isEmpty()) {
                System.out.println("Username cannot be empty\n");
                continue;
            }

            try {
                List<String> userInfo = Problem2c.retrieveUserInfo(username);

                if (userInfo != null) {
                    loginUser(username, userInfo);
                } else {
                    handleEnrollment(username);
                }
            } catch (IOException e) {
                System.out.println("Error retrieving user data: " + e.getMessage());
            }
        }
    }


    private static void loginUser(String username, List<String> userInfo) {
        while (true) {
            System.out.print("Password (or 'E' to exit): ");
            String password = INPUT.nextLine();

            if (password.equalsIgnoreCase(EXIT_COMMAND)) {
                displayWelcome();
                return;
            }
            
            if (Problem4ab.loginUserWithCredentials(username, password)) {
                operationsMenu(userInfo);
                return;
            } else {
                System.out.println("Invalid password. Please try again.\n");
            }
        }
    }


    private static void operationsMenu(List<String> userInfo) {
        String role = userInfo.get(2);
        System.out.println("Login successful\n");

        while (true) {
            System.out.println("Select operation (1-7 or 'E' to exit):");
            System.out.print("> ");
            String operation = INPUT.nextLine();

            if (operation.equalsIgnoreCase(EXIT_COMMAND)) {
                displayWelcome();
                return;
            }
            
            LocalTime currentTime = LocalTime.now();
            boolean hasAccess = Problem1c.validateOperationAccess(role, operation, currentTime);

            if (hasAccess) {
                System.out.println("Operation " + operation + " authorized for " + role);
            } else {
                System.out.println("Operation " + operation + " not authorized for " + role);
            }
        }
    }


    private static void handleEnrollment(String username) {
        System.out.println("User not found");
        System.out.print("Would you like to enroll? (Y/N): ");
        String response = INPUT.nextLine().trim().toUpperCase();

        if (!response.equals("Y")) {
            displayWelcome();
            return;
        }

        try {
            Problem3a.enrollUser();
            System.out.println("Enrollment complete\n");

            System.out.print("Would you like to login? (Y/N): ");
            String loginResponse = INPUT.nextLine().trim().toUpperCase();

            if (loginResponse.equals("Y")) {
                displayWelcome();
            }
        } catch (Exception e) {
            System.out.println("Enrollment failed: " + e.getMessage());
        }
    }
    private static void seed() throws Exception {
        final String commonPassword = "Test12345$";
        String[][] users = {
                {"Sasha_Kim", "Client"},
                {"Emery_Blake", "Client"},
                {"Noor_Abbasi", "Premium Client"},
                {"Zuri_Adebayo", "Premium Client"},
                {"Mikael_Chen", "Financial Advisor"},
                {"Jordan_Riley", "Financial Advisor"},
                {"Ellis_Nakamura", "Financial Planner"},
                {"Harper_Diaz", "Financial Planner"},
                {"Alex_Hayes", "Teller"},
                {"Adair_Patel", "Teller"}
        };
        for (String[] u : users) {
            String username = u[0];
            String role = u[1];
            Problem2c.addUser(username, commonPassword, role);
        }
    }
}






