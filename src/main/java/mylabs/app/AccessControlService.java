package mylabs.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessControlService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public List<String> getAvailableOperations(User user) {
        if (user == null || user.getRole() == null) {
            return List.of();
        }

        if ("Teller".equals(user.getRole().getName()) && !isBusinessHours()) {
            return List.of();
        }

        List<RolePermission> rolePermissions = rolePermissionRepository
                .findPermissionsByRoleName(user.getRole().getName());

        return rolePermissions.stream()
                .map(rp -> rp.getPermission().getName())
                .collect(Collectors.toList());
    }

    public boolean canPerformOperation(User user, String operation) {
        if (user == null || user.getRole() == null || operation == null) {
            return false;
        }

        if ("Teller".equals(user.getRole().getName()) && !isBusinessHours()) {
            return false;
        }

        return rolePermissionRepository.existsByRoleNameAndPermissionName(
                user.getRole().getName(), operation);
    }

    private boolean isBusinessHours() {
        LocalTime now = LocalTime.now();
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(17, 0);
        return !now.isBefore(start) && !now.isAfter(end);
    }
}