package com.github.psycomentis06.isatexbackendservice.controller.api.admin;

import com.github.psycomentis06.isatexbackendservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")

    @GetMapping("/all")
    public ResponseEntity<Object> all() {
        return new ResponseEntity<>(userService.allUsers(), null, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> all(
            @PathVariable int id
    ) {
        return new ResponseEntity<>(userService.getUser(id), null, HttpStatus.OK);
    }
}
