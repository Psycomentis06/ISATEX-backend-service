package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.Role;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class BaseDBData {
    private final RoleRepository roleRepository;
    private final UserService userService;

    public BaseDBData(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Transactional
    public void saveRequiredRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new Role(Role.ROLE_FORWARDING_AGENT));
        roles.add(new Role(Role.ROLE_ADMIN));
        roles.add(new Role(Role.ROLE_CUSTOMER));
        roles.add(new Role(Role.ROLE_SECRETARY));
        roles.add(new Role(Role.ROLE_STOREKEEPER));
        roles.add(new Role(Role.ROLE_PRIVILEGED_CUSTOMER));
        roles.add(new Role(Role.ROLE_CUSTOMS_OFFICER));

        roleRepository.saveAll(roles);
    }

    @Transactional
    public void saveRequiredUser() {
        Optional<User> userOptional = userService.getUserByUsernameOrPassword(User.class, "admin");
        userOptional.ifPresentOrElse(u -> {}, () -> {

            User user = new User();
            user
                    .setEmail("admin@isatex.tn")
                    .setPassword("123456789")
                    .setVerified(true)
                    .setUsername("admin")
                    .setFirstName("admin")
                    .setLastName("admin");

            Optional<Role> adminRole = roleRepository.findById(Role.ROLE_ADMIN);
            Role role;
            if (adminRole.isEmpty()) {
                role = roleRepository.save(new Role(Role.ROLE_ADMIN));
            } else {
                role = adminRole.get();
            }
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);

            userService.createUser(user);
        });
    }
}
