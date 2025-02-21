package com.sg.billback.initializer;

import jakarta.transaction.Transactional;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.sg.billback.model.AppRole;
import com.sg.billback.model.Role;
import com.sg.billback.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleInitializer {

    private final RoleRepository roleRepository;
    private final JdbcTemplate jdbcTemplate;  // Direct SQL execution

    public RoleInitializer(RoleRepository roleRepository, JdbcTemplate jdbcTemplate) {
        this.roleRepository = roleRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initRoles() {
        System.out.println("Checking and synchronizing roles...");

        // Step 1: Convert Enum Values to a List
        List<String> enumRoleNames = Arrays.stream(AppRole.values())
                .map(Enum::name)
                .toList();

        // Step 2: **Delete roles from DB that are no longer in AppRole**
        String deleteQuery = "DELETE FROM roles WHERE role_name NOT IN (" +
                String.join(",", enumRoleNames.stream().map(role -> "'" + role + "'").toList()) + ")";
        jdbcTemplate.execute(deleteQuery);

        // Step 3: **Insert missing roles**
        Arrays.stream(AppRole.values()).forEach(roleEnum -> {
            roleRepository.findByRoleName(roleEnum)
                    .orElseGet(() -> {
                        Role role = new Role(roleEnum);
                        System.out.println("Inserting role: " + roleEnum);
                        return roleRepository.save(role);
                    });
        });

        System.out.println("Role synchronization completed.");
    }
}
