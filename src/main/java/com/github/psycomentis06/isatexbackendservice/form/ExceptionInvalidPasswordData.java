package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExceptionInvalidPasswordData extends ExceptionBaseData{
    private List<String> constraints;
}
