package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionBaseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class EntityNotFoundExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionBaseData> entityNotFound(EntityNotFoundException e) {

        ExceptionBaseData baseData = new ExceptionBaseData();
        baseData.setMessage(e.getMessage())
                .setCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(baseData, HttpStatus.NOT_FOUND);
    }
}
