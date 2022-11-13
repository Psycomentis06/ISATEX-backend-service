package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.repository.RoleRepository;
import com.github.psycomentis06.isatexbackendservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public void createUser(User user) {

    }
    public void createCustomer(Customer customer) {}
    public void customerToPrivilegedCustomer(Customer customer) {}

    //NOTE Can we switch between user types
    public void authenticate(String username, String password) {}
    public void sendVerificationKey(String email) {}
    public void resetPassword(int userId, String newPass, String newPassRetype) {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsernameOrEmail(User.class, username, username);
        User user = userOptional.orElseThrow(
                () -> new UsernameNotFoundException("User Not Found")
        );

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(
                role -> { authorities.add(new SimpleGrantedAuthority(role.getName()));}
        );
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }
}
