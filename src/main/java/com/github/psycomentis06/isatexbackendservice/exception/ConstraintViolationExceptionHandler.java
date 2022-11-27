package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionConstraintData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ConstraintViolationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ExceptionConstraintData> violation(ConstraintViolationException e) {
        Map<String, String> constraints = new HashMap<>();
        System.out.println("Print constraint exception from ConstraintViolationExceptionHandler");
        e.printStackTrace();
        e.getConstraintViolations().forEach(c -> {
            /*
            System.out.println(c.getPropertyPath().toString());
            System.out.println("-------------------");
            System.out.println(Arrays.toString(new Map[]{c.getConstraintDescriptor().getAttributes()}));
             */
            constraints.put(c.getPropertyPath().toString(), c.getMessage());
        });
        ExceptionConstraintData constraintDataResp = new ExceptionConstraintData();
        constraintDataResp.setCode(HttpStatus.BAD_REQUEST.value());
        constraintDataResp.setMessage("Can't perform operation due to constraints violation");
        constraintDataResp.setConstraints(constraints);
        return new ResponseEntity<>(constraintDataResp, HttpStatus.BAD_REQUEST);
    }
}
