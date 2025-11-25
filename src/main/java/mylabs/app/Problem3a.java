//Zachary Gallant 101272210
package mylabs.app;
import java.util.Scanner;

public class Problem3a {

    private static Scanner input = new Scanner(System.in);


    public static void enrollUser() {
        String selectedUsername = obtainUsername();
        if (selectedUsername == null) return;

        String selectedPassword = obtainPassword(selectedUsername);
        if (selectedPassword == null) return;

        String selectedRole = obtainRole();

        try {
            Problem2c.addUser(selectedUsername, selectedPassword, selectedRole);
            System.out.println("\nRegistration successful!");
            System.out.println("Username: " + selectedUsername);
            System.out.println("Role: " + selectedRole);
        } catch (Exception e) {
            System.out.println("\nRegistration failed: " + e.getMessage());
        }
    }


    private static String obtainUsername() {
        while (true) {
            System.out.print("\nEnter username: ");
            String candidateName = input.nextLine().trim();

            if (candidateName.isEmpty()) {
                System.out.println("Error: Username cannot be empty");
                continue;
            }

            try {
                if (Problem2c.retrieveUserInfo(candidateName) != null) {
                    System.out.println("Error: Username already in use");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Error: Could not verify if username is unique");
                continue;
            }

            return candidateName;
        }
    }


    private static String obtainPassword(String username) {
        while (true) {
            System.out.print("\nEnter password: ");
            System.out.println("Type 'E' to cancel");

            String candidatePassword = input.nextLine();

            if (candidatePassword.equalsIgnoreCase("E")) {
                System.out.println("Cancelled.");
                return null;
            }

            if (!Problem3b.isValidPassword(candidatePassword, username)) {
                System.out.println("Password requirements not met. Try again.\n");
                continue;
            }

            return candidatePassword;
        }
    }


    private static String obtainRole() {
        String[] roleOptions = {
                "Client",
                "Premium Client",
                "Financial Advisor",
                "Financial Planner",
                "Teller"
        };

        while (true) {
            System.out.println("\nSelect your role:");
            for (int idx = 0; idx < roleOptions.length; idx++) {
                System.out.println("  " + (idx + 1) + ". " + roleOptions[idx]);
            }

            System.out.print("Choose (1-5): ");
            String selection = input.nextLine().trim();

            if (!isNumericInRange(selection, 1, 5)) {
                System.out.println("Invalid selection. Please choose 1-5.\n");
                continue;
            }

            int selectedIndex = Integer.parseInt(selection) - 1;
            return roleOptions[selectedIndex];
        }
    }


    private static boolean isNumericInRange(String value, int min, int max) {
        try {
            int num = Integer.parseInt(value);
            return num >= min && num <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean checkRole(String roleName) {
        return roleName.equals("Client") ||
                roleName.equals("Premium Client") ||
                roleName.equals("Financial Advisor") ||
                roleName.equals("Financial Planner") ||
                roleName.equals("Teller");
    }
}
