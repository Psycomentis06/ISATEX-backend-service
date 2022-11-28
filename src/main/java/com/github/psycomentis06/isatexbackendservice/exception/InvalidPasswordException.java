package com.github.psycomentis06.isatexbackendservice.exception;

import java.util.List;

public class InvalidPasswordException extends RuntimeException{
    private int code;
    private List<String> constraints;

    public InvalidPasswordException(String message) {
        super(message);

    }


    public InvalidPasswordException(String message, int code) {
        super(message);
        this.code = code;
    }

    public InvalidPasswordException(String message, int code, List<String> constraints) {
        super(message);
        this.code = code;
        this.constraints = constraints;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<String> constraints) {
        this.constraints = constraints;
    }
}
