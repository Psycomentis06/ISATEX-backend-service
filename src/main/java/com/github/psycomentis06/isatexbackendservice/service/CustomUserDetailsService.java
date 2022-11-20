package com.github.psycomentis06.isatexbackendservice.service;

import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    CustomUserDetailsService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findUserByUsernameOrEmail(User.class, username, username);
        System.out.println("User");
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
