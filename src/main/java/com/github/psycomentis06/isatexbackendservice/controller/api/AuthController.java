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
import java.util.HashMap;
import java.util.Map;

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
        Map<String , String> resp = new HashMap<>();
        resp.put("accessToken", tokenService.generateAccessToken(authentication, request.getRequestURI()));
        resp.put("refreshToken", tokenService.generateRefreshToken(authentication, request.getRequestURI()));
        return new ResponseEntity<>(resp, null, HttpStatus.OK);
    }

}
