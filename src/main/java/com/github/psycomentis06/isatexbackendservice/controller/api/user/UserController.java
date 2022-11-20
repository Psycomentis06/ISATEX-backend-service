package com.github.psycomentis06.isatexbackendservice.controller.api.user;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import com.github.psycomentis06.isatexbackendservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("userUserController")
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(
            @RequestBody Customer customer
    ) {
        userService.createCustomer(customer);
        return new ResponseEntity<>("create", null, HttpStatus.OK);
    }
}
