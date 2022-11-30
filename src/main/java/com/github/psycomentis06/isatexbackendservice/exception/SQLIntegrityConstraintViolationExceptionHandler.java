package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionBaseData;
import com.github.psycomentis06.isatexbackendservice.form.ExceptionConstraintData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class SQLIntegrityConstraintViolationExceptionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionBaseData> integrityConstraintViolation(SQLIntegrityConstraintViolationException e) {
        ExceptionBaseData constraintData = new ExceptionBaseData();
        constraintData
                .setCode(HttpStatus.FORBIDDEN.value())
                .setMessage(e.getMessage());
        if (e.getErrorCode() == 1062) {
            // duplicate entry
            String duplicateValue = e.getMessage().split("'")[1];
            constraintData.setMessage(duplicateValue + " user already exists.");
        }
        return new ResponseEntity<>(constraintData, HttpStatus.FORBIDDEN);
    }
}
