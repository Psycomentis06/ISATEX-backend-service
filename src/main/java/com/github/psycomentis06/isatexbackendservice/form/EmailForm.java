package com.github.psycomentis06.isatexbackendservice.form;

import lombok.*;
import lombok.experimental.Accessors;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class EmailForm {
    private String recipient;
    private String subject;
    private String messageBody;
    private String htmlBody;
    private String attachment;
}
