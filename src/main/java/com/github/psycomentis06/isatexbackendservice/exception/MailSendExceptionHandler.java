package com.github.psycomentis06.isatexbackendservice.exception;

import com.github.psycomentis06.isatexbackendservice.form.ExceptionBaseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MailSendExceptionHandler {

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ExceptionBaseData> mailSendException(MailSendException e) {
        ExceptionBaseData baseData = new ExceptionBaseData();
        baseData
                .setMessage("Could not perform operation, We weren't able to connect to the mail server at the moment. " +
                        "Try again later or contact the admin")
        .setCode(503);
        return new ResponseEntity<>(baseData, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
