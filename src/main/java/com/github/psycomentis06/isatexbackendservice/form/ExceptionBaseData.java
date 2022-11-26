package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ExceptionBaseData {
    private String message;
    private Integer code;
}
