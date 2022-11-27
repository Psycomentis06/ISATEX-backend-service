package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import com.github.psycomentis06.isatexbackendservice.entity.Role;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.exception.InvalidPasswordException;
import com.github.psycomentis06.isatexbackendservice.exception.UsernameNotFoundException;
import com.github.psycomentis06.isatexbackendservice.repository.CustomerRepository;
import com.github.psycomentis06.isatexbackendservice.repository.RoleRepository;
import com.github.psycomentis06.isatexbackendservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    private final RedisService redisService;

    UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, CustomerRepository customerRepository, RedisService redisService) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.customerRepository = customerRepository;
        this.redisService = redisService;
    }

    public void createUser(User user) {
        checkPassword(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public void checkPassword(String password) {
        List<String> constraints = new ArrayList<>();
        if (password == null) {
            constraints.add("Password is missing");
        } else {
            if (password.length() > 16) {
                constraints.add("Password length bigger than 16");
            }

            if (password.length() < 6) {
                constraints.add("Password length shorter than 6");
            }
        }

        if (constraints.size() > 0) {
            throw new InvalidPasswordException("Malformed password", 400, constraints);
        }
    }

    public void createCustomer(Customer customer) {
        createCustomer(customer, false);
    }

    public void createCustomer(Customer customer, boolean verified) {
        checkPassword(customer.getPassword());
        customer.setVerified(verified);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        System.out.println("PLength=" + customer.getPassword().length());
        System.out.println(customer.getPassword());
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

    @Transactional
    public void resetPassword(int userId, String newPass, String newPassRetype, String token) {
        List<String> constraints = new ArrayList<>();
        if (newPass == null) {
           constraints.add("New password is missing");
        } else if (newPassRetype == null){
            constraints.add("Password retype is missing");
        } else {
            if (!newPass.equals(newPassRetype)) {
                constraints.add("Password and Retype password does not match");
            }
            String savedToken = redisService.getResetPasswordToken(String.valueOf(userId));
            if (savedToken == null) {
                constraints.add("Request link expired try again");
            } else {
                if (!savedToken.equals(token)) {
                    constraints.add("Given token does not match the token sent to your Email ");
                } else {
                    Optional<User> user = getUser(userId);
                    user.ifPresentOrElse(u -> {
                        u.setPassword(newPass);
                        createUser(u);
                        redisService.removeResetPasswordToken(String.valueOf(userId));
                    }, () -> {
                        throw new UsernameNotFoundException("Can't reset password. Given user is not found", HttpStatus.BAD_REQUEST.value());
                    });
                }
            }
        }
        if (constraints.size() > 0) {
            throw new InvalidPasswordException("Reset password failed", HttpStatus.BAD_REQUEST.value(), constraints);
        }
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

    public <T> Optional<T> getUserByUsernameOrPassword(Class<T> classType, String value) {
        Optional<T> userOptional = userRepository.findByUsername(classType, value);
        if (userOptional.isPresent()) return userOptional;
        return userRepository.findByEmail(classType, value);
    }

    public <T> Page<T> getAll(Pageable pageable, Class<T> type) {
        return userRepository.findAll(pageable, type);
    }
}