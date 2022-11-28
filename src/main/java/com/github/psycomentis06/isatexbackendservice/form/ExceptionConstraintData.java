package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionConstraintData extends ExceptionBaseData{
    Map<String, String> constraints;
}
