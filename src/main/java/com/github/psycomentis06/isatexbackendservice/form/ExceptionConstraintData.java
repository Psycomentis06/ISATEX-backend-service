package com.github.psycomentis06.isatexbackendservice.form;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ExceptionConstraintData extends ExceptionBaseData{
    Map<String, String> constraints;
}
