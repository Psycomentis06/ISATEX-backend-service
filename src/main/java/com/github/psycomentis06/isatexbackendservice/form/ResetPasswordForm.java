package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ResetPasswordForm {
    private String newPassword;
    private String passwordRetype;
}
