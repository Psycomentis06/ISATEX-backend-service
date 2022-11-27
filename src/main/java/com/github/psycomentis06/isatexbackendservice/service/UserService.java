package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import com.github.psycomentis06.isatexbackendservice.entity.Role;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.repository.CustomerRepository;
import com.github.psycomentis06.isatexbackendservice.repository.RoleRepository;
import com.github.psycomentis06.isatexbackendservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final CustomerRepository customerRepository;

    UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void createCustomer(Customer customer) {
        createCustomer(customer, false);
    }
    public void createCustomer(Customer customer, boolean verified) {
        customer.setVerified(verified);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        ArrayList<Role> roles = new ArrayList<>();
        Optional<Role> role = roleRepository.findById(Role.ROLE_CUSTOMER);
        role.ifPresent(r -> {
            roles.add(r);
            customer.setRoles(roles);
            customerRepository.save(customer);
        });
    }

    //NOTE Can we switch between user types
    public void authenticate(String username, String password) {
    }

    public void sendVerificationKey(String email) {
    }

    public void resetPassword(int userId, String newPass, String newPassRetype) {
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public <T> Optional<T> getUser(Class<T> classType, String username, String email) {
        return userRepository.findUserByUsernameOrEmail(classType, username, email);
    }
}