package com.github.psycomentis06.isatexbackendservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JwtFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            byte[] body = StreamUtils.copyToByteArray(request.getInputStream());
            Map loginForm = mapper.readValue(body, Map.class);
            System.out.println("Username: " + loginForm.get("username") + ", Password: " + loginForm.get("password"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.get("username"), loginForm.get("password"));
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        // Algorithm algo = Algorithm.ECDSA256();
    }
}
