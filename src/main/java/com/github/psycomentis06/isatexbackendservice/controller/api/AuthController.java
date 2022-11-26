package com.github.psycomentis06.isatexbackendservice.controller.api;

import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.form.EmailForm;
import com.github.psycomentis06.isatexbackendservice.form.LoginForm;
import com.github.psycomentis06.isatexbackendservice.service.EmailService;
import com.github.psycomentis06.isatexbackendservice.service.RedisService;
import com.github.psycomentis06.isatexbackendservice.service.TokenService;
import com.github.psycomentis06.isatexbackendservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final RedisService redisService;

    private final UserService userService;

    private final EmailService emailService;

    @Value("${app.auth.reset-password-url}")
    private String resetPasswordUrl;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService, RedisService redisService, UserService userService, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.redisService = redisService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginForm loginForm, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        Map<String, String> resp = new HashMap<>();
        resp.put("accessToken", tokenService.generateAccessToken(authentication, request.getRequestURL().toString()));
        resp.put("refreshToken", tokenService.generateRefreshToken(authentication, request.getRequestURL().toString()));
        return new ResponseEntity<>(resp, null, HttpStatus.OK);
    }

    @PostMapping("/password/reset/request")
    public ResponseEntity<Object> sendResetRequest(
            @RequestBody String email
    ) {
        Optional<User> user = userService.getUser(User.class, "", email);
        user.ifPresentOrElse(u -> {
            String token = UUID.randomUUID().toString();
            EmailForm emailForm = new EmailForm();
            String resetUrl = resetPasswordUrl + "?id=" + u.getId() + "&token=" + token;
            emailForm
                    .setSubject("Reset Password")
                    .setRecipient(email)
                    .setMessageBody(
                            "Reset password link:" +
                                    resetUrl
                    );
            emailService.sendEmail(emailForm);
            redisService.setUserResetPasswordToken(Integer.toString(u.getId()), token);
        }, () -> {
            throw new UsernameNotFoundException("User not found");
        });
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Reset password request sent to given Email");
        return new ResponseEntity<>(resp, null, HttpStatus.OK);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<Object> resetPassword(
            @RequestParam(name = "id", defaultValue = "", required = true) String email,
            @RequestParam(name = "token", defaultValue = "", required = true) String token
    ) {
        return new ResponseEntity<>("res", null, HttpStatus.OK);
    }

}
