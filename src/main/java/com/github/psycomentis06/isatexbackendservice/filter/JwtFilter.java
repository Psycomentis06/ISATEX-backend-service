package com.github.psycomentis06.isatexbackendservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.form.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class JwtFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public JwtFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper mapper = new ObjectMapper();
        try {
            byte[] inputStreamBytes = request.getInputStream().readAllBytes();
            /*Map<String, String> jsonRequest = mapper.readValue(inputStreamBytes, Map.class);
            LoginForm loginForm = mapper.readValue(jsonRequest.get("body"), LoginForm.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
            return authenticationManager.authenticate(token);*/
            byte[] body = StreamUtils.copyToByteArray(request.getInputStream());
            Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);
            System.out.println(jsonRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        // Algorithm algo = Algorithm.ECDSA256();
    }
}
