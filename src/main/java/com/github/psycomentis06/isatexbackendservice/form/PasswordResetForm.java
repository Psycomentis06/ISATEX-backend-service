package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PasswordResetForm {
    String password;
    String retypePassword;
}
