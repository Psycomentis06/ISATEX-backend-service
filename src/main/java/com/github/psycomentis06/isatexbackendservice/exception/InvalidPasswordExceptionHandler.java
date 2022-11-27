package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionInvalidPasswordData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidPasswordExceptionHandler {
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ExceptionInvalidPasswordData> invalidPasswordDataResponseEntity(InvalidPasswordException e) {
       ExceptionInvalidPasswordData invalidPasswordData = new ExceptionInvalidPasswordData();
       invalidPasswordData
               .setConstraints(e.getConstraints())
               .setCode(e.getCode())
               .setMessage(e.getMessage());
        return new ResponseEntity<>(invalidPasswordData, HttpStatus.BAD_REQUEST);
    }
}
