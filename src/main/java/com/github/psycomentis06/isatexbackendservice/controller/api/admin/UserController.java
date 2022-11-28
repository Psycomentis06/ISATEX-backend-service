package com.github.psycomentis06.isatexbackendservice.controller.api.admin;

import com.github.psycomentis06.isatexbackendservice.entity.Customer;
import com.github.psycomentis06.isatexbackendservice.entity.User;
import com.github.psycomentis06.isatexbackendservice.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("adminUserController")
@RequestMapping("/api/admin/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(
            @RequestBody User user
            ) {
        userService.createUser(user);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "User created successfully");
        resp.put("code", "200");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/customer/create")
    public ResponseEntity<Object> createCustomer(
            @RequestBody Customer customer
            ) {
        userService.createCustomer(customer, true);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Customer created successfully");
        resp.put("code", "200");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<Object> getAll(
            @RequestParam(name = "s", required = false, defaultValue = "10") int s,
            @RequestParam(name = "p", required = false, defaultValue = "0") int p,
            @RequestParam(name = "q", required = false, defaultValue = "") String query
    ) {
        Pageable pageable = Pageable.ofSize(s);
        return new ResponseEntity<>(userService.getAll(User.class, pageable.withPage(p), query), null, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(userService.getUser(id), null, HttpStatus.OK);
    }
}
