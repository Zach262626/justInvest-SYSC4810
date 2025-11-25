package mylabs.app;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem1c {

    private static final Map<String, String[]> roleAccessMap = new HashMap<>();

    static {
        roleAccessMap.put("C", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "4.View Financial Advisor Contact info"});
        roleAccessMap.put("PC", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "3.Modify Investment Portfolio", "4.View Financial Advisor Contact info", "5.View Financial Planner Contact info"});
        roleAccessMap.put("FA", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "3.Modify Investment Portfolio", "7.View Private Consumer Instruments"});
        roleAccessMap.put("FP", new String[]{"1.View Account Balance", "2.View Investment Portfolio", "3.Modify Investment Portfolio", "6.View Money Market Instruments"});
        roleAccessMap.put("T", new String[]{"1.View Account Balance", "2.View Investment Portfolio"});
    }

    public static void displayRolePermissions(String role) {
        String[] userPermissions = roleAccessMap.get(role);

        if (userPermissions != null) {
            for (String perm : userPermissions) {
                System.out.println(perm);
            }
        } else {
            System.out.println("Invalid role selected.");
        }
    }

    public static boolean isWithinBusinessHours(LocalTime currentTime) {
        return currentTime.isAfter(LocalTime.of(8, 59)) && currentTime.isBefore(LocalTime.of(17, 1));
    }

    public static boolean validateOperationAccess(String userRole, String operationId, LocalTime currentTime) {
        // Check if role is valid
        if (!roleAccessMap.containsKey(userRole)) {
            System.out.println("Invalid role selected.");
            return false;
        }

        //Check if Teller is within business hours
        if (userRole.equals("T") && !isWithinBusinessHours(currentTime)) {
            System.out.println("Tellers can only access the system between 9:00 AM and 5:00 PM.");
            return false;
        }

        //Check if operation is valid for the role
        if (isValidOperation(userRole, operationId)) {
            System.out.println("Operation Successful!");
            return true;
        } else {
            System.out.println("Invalid operation/option selected.");
            return false;
        }
    }

    private static boolean isValidOperation(String role, String operationId) {
        String[] permissions = roleAccessMap.get(role);
        if (permissions == null) return false;

        for (String perm : permissions) {
            //"1.View Account Balance" → ["1", "View Account Balance"]
            String opNumber = perm.split("\\.")[0];
            if (opNumber.equals(operationId)) {
                return true;
            }
        }
        return false;
    }
}
