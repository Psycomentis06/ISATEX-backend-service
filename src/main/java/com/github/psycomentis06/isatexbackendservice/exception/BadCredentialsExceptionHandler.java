package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionBaseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BadCredentialsExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionBaseData> badCredentials(BadCredentialsException e) {
        ExceptionBaseData baseData = new ExceptionBaseData();
        baseData.setCode(HttpStatus.FORBIDDEN.value())
                .setMessage(e.getMessage());
        return new ResponseEntity<>(baseData, HttpStatus.FORBIDDEN);
    }
}
