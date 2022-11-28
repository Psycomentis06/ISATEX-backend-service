package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ExceptionBaseData {
    private String message;
    private Integer code;
}
