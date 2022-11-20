package com.github.psycomentis06.isatexbackendservice.controller.api;

import com.github.psycomentis06.isatexbackendservice.form.LoginForm;
import com.github.psycomentis06.isatexbackendservice.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
         Authentication authentication = authenticationManager.authenticate(token);
        return new ResponseEntity<>(tokenService.generateAccessToken(authentication, request.getRequestURL().toString()), null, HttpStatus.OK);
    }

}
