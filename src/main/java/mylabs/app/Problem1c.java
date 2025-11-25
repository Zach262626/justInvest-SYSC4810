//Zachary Gallant 101272210
package mylabs.app;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;


public class Problem1c {

    private static final Map<Integer, String> GLOBAL_PERMISSIONS = new HashMap<>();
    private static final Map<String, int[]> ROLE_PERMISSIONS = new HashMap<>();

    static {
        GLOBAL_PERMISSIONS.put(1, "View Account Balance");
        GLOBAL_PERMISSIONS.put(2, "View Investment Portfolio");
        GLOBAL_PERMISSIONS.put(3, "Modify Investment Portfolio");
        GLOBAL_PERMISSIONS.put(4, "View Financial Advisor Contact");
        GLOBAL_PERMISSIONS.put(5, "View Financial Planner Contact");
        GLOBAL_PERMISSIONS.put(6, "View Money Market Instruments");
        GLOBAL_PERMISSIONS.put(7, "View Private Consumer Instruments");

        ROLE_PERMISSIONS.put("Client", new int[]{1, 2, 4});
        ROLE_PERMISSIONS.put("Premium Client", new int[]{1, 2, 4, 3, 5});
        ROLE_PERMISSIONS.put("Financial Advisor", new int[]{1, 2, 3, 7});
        ROLE_PERMISSIONS.put("Financial Planner", new int[]{1, 2, 3, 7, 6});
        ROLE_PERMISSIONS.put("Teller", new int[]{1, 2});
    }

    public static boolean checkTime(LocalTime time) {
        LocalTime businessStart = LocalTime.of(9, 0);   // 9:00 AM
        LocalTime businessEnd = LocalTime.of(17, 0);    // 5:00 PM (17:00)

        return !time.isBefore(businessStart) && !time.isAfter(businessEnd);
    }

    public static void showPermissions(String role) {
        int[] permissionIds = ROLE_PERMISSIONS.get(role);
        if (permissionIds != null && permissionIds.length > 0) {
            for (int id : permissionIds) {
                String permission = GLOBAL_PERMISSIONS.get(id);
                System.out.println("  " + id + ". " + permission);
            }
        } else {
            System.out.println("  No permissions found for role: " + role);
        }
    }

    public static boolean validateOperationAccess(String userRole, String operationId, LocalTime currentTime) {
        if (userRole == null || operationId == null) {
            return false;
        }
        int[] permissionIds = ROLE_PERMISSIONS.get(userRole);
        if (permissionIds == null) {
            return false; // Invalid role
        }
        int opId;
        try {
            opId = Integer.parseInt(operationId);
        } catch (NumberFormatException e) {
            return false;
        }
        if (opId < 1 || opId > 7) {
            System.out.println("here");
            return false;
        }
        boolean hasPermission = false;
        for (int id : permissionIds) {
            if (id == opId) {
                hasPermission = true;
                break;
            }
        }
        if (!hasPermission) {
            return false;
        }

        if (userRole.equals("Teller")) {
            return checkTime(currentTime);
        }
        return true;
    }

}