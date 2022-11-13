package com.github.psycomentis06.isatexbackendservice.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class LoginForm {
    private String username;
    private String password;
}
