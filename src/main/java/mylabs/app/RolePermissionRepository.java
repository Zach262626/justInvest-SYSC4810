package mylabs.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRoleName(String roleName);

    @Query("SELECT rp FROM RolePermission rp WHERE rp.role.name = :roleName")
    List<RolePermission> findPermissionsByRoleName(@Param("roleName") String roleName);

    boolean existsByRoleNameAndPermissionName(String roleName, String permissionName);
}
