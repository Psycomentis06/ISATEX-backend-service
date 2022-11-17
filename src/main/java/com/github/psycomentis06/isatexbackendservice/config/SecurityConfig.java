package com.github.psycomentis06.isatexbackendservice.config;

import com.github.psycomentis06.isatexbackendservice.filter.JwtFilter;
import com.github.psycomentis06.isatexbackendservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   @Value("${jwt.public.key}")
   RSAPublicKey publicKey;

   @Value("${jwt.private.key}")
   RSAPrivateKey privateKey;
   private AuthenticationManager authenticationManager;

   private BCryptPasswordEncoder bCryptPasswordEncoder;

   private UserService userService;

   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf()
              .disable()
           .cors()
           .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
           .authorizeRequests()
                  .anyRequest()
                  //.authenticated()
              .permitAll()
              .and()
             .addFilter(new JwtFilter(authenticationManager));
      return http.build();
   }

   /*@Bean
   public DaoAuthenticationProvider daoAuthenticationProvider() {
      DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
              provider.setUserDetailsService(userDetailsService());
              provider.setPasswordEncoder(passwordEncoder());
              return provider;
   }*/

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   UserDetailsService userDetailsService() {
      return new UserService();
   }


   @Bean
   AuthenticationManager authenticationManagerBean(AuthenticationManagerBuilder managerBuilder) throws Exception {
      return managerBuilder.getOrBuild();
   }
}
