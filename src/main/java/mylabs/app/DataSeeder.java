package mylabs.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createRolesAndPermissions();
        createSampleUsers();
    }

    private void createRolesAndPermissions() {
        // Create permissions
        List<Permission> permissions = Arrays.asList(
                new Permission("View Account Balance", "View account balance"),
                new Permission("View Investment Portfolio", "View investment portfolio"),
                new Permission("Modify Investment Portfolio", "Modify investment portfolio"),
                new Permission("View Financial Advisor Contact", "View financial advisor contact details"),
                new Permission("View Financial Planner Contact", "View financial planner contact details"),
                new Permission("View Money Market Instruments", "View money market instruments"),
                new Permission("View Private Consumer Instruments", "View private consumer instruments")
        );
        permissionRepository.saveAll(permissions);

        // Create roles
        List<Role> roles = Arrays.asList(
                new Role("Client"),
                new Role("Premium Client"),
                new Role("Financial Advisor"),
                new Role("Financial Planner"),
                new Role("Teller")
        );
        roleRepository.saveAll(roles);

        //Map roles to permissions
        Role client = roleRepository.findByName("Client").get();
        Role premiumClient = roleRepository.findByName("Premium Client").get();
        Role financialAdvisor = roleRepository.findByName("Financial Advisor").get();
        Role financialPlanner = roleRepository.findByName("Financial Planner").get();
        Role teller = roleRepository.findByName("Teller").get();

        createRolePermission(client, "View Account Balance");
        createRolePermission(client, "View Investment Portfolio");
        createRolePermission(client, "View Financial Advisor Contact");

        createRolePermission(premiumClient, "View Account Balance");
        createRolePermission(premiumClient, "View Investment Portfolio");
        createRolePermission(premiumClient, "Modify Investment Portfolio");
        createRolePermission(premiumClient, "View Financial Advisor Contact");
        createRolePermission(premiumClient, "View Financial Planner Contact");

        createRolePermission(financialAdvisor, "View Account Balance");
        createRolePermission(financialAdvisor, "View Investment Portfolio");
        createRolePermission(financialAdvisor, "Modify Investment Portfolio");
        createRolePermission(financialAdvisor, "View Private Consumer Instruments");

        createRolePermission(financialPlanner, "View Account Balance");
        createRolePermission(financialPlanner, "View Investment Portfolio");
        createRolePermission(financialPlanner, "Modify Investment Portfolio");
        createRolePermission(financialPlanner, "View Private Consumer Instruments");
        createRolePermission(financialPlanner, "View Money Market Instruments");

        createRolePermission(teller, "View Account Balance");
        createRolePermission(teller, "View Investment Portfolio");
    }

    private void createRolePermission(Role role, String permissionName) {
        Permission permission = permissionRepository.findByName(permissionName).get();
        RolePermission rolePermission = new RolePermission(role, permission);
        rolePermissionRepository.save(rolePermission);
    }

    private void createSampleUsers() {
        Role client = roleRepository.findByName("Client").get();
        Role premiumClient = roleRepository.findByName("Premium Client").get();
        Role financialAdvisor = roleRepository.findByName("Financial Advisor").get();
        Role financialPlanner = roleRepository.findByName("Financial Planner").get();
        Role teller = roleRepository.findByName("Teller").get();

        List<User> users = Arrays.asList(
                new User("Sasha Kim", "123", client),
                new User("Emery Blake", "123", client),
                new User("Noor Abbasi", "123", premiumClient),
                new User("Zuri Adebayo", "123", premiumClient),
                new User("Mikael Chen", "123", financialAdvisor),
                new User("Jordan Riley", "123", financialAdvisor),
                new User("Ellis Nakamura", "123", financialPlanner),
                new User("Harper Diaz", "123", financialPlanner),
                new User("Alex Hayes", "123", teller),
                new User("Adair Patel", "123", teller)
        );

        userRepository.saveAll(users);
    }
}