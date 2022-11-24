package com.github.psycomentis06.isatexbackendservice.controller.api;

import com.github.psycomentis06.isatexbackendservice.form.LoginForm;
import com.github.psycomentis06.isatexbackendservice.service.RedisService;
import com.github.psycomentis06.isatexbackendservice.service.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private RedisService redisService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, RedisService redisService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.redisService = redisService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
         Authentication authentication = authenticationManager.authenticate(token);
        Map<String , String> resp = new HashMap<>();
        resp.put("accessToken", tokenService.generateAccessToken(authentication, request.getRequestURL().toString()));
        resp.put("refreshToken", tokenService.generateRefreshToken(authentication, request.getRequestURL().toString()));
        return new ResponseEntity<>(resp, null, HttpStatus.OK);
    }

    @PostMapping("/password/reset/request")
    public ResponseEntity<Object> sendResetRequest(
        @RequestBody String email
    )  {
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        String token = UUID.randomUUID().toString();
       redisService.setUserResetPasswordToken("1", generatedString);
        return new ResponseEntity<>(token, null, HttpStatus.OK);
    }

}
