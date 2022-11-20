package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.repository.RoleRepository;
import com.github.psycomentis06.isatexbackendservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    //NOTE Can we switch between user types
    public void authenticate(String username, String password) {
    }

    public void sendVerificationKey(String email) {
    }

    public void resetPassword(int userId, String newPass, String newPassRetype) {
    }
}
