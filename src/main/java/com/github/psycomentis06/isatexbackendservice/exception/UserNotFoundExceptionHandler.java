package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionBaseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionBaseData>userNotFound(UsernameNotFoundException e) {
        ExceptionBaseData baseData = new ExceptionBaseData();
        baseData.setCode(e.getCode());
        baseData.setMessage(e.getMessage());
        e.printStackTrace();
       return new ResponseEntity<>(baseData, null, HttpStatus.NOT_FOUND);
    }
}
