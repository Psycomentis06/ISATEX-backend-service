package com.github.psycomentis06.isatexbackendservice.controller.api.customer;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import com.github.psycomentis06.isatexbackendservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class CustomerController {

    private final UserService userService;

    private final Validator validator;


    public CustomerController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(
            @RequestBody Customer customer
    ) {
        userService.createCustomer(customer);
        Map<String, String> resp = new HashMap<>();
        resp.put("code", "200");
        resp.put("message", "Customer created successfully");
        return new ResponseEntity<>(resp, null, HttpStatus.OK);
    }
}
