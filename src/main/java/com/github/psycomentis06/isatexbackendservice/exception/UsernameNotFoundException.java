package com.github.psycomentis06.isatexbackendservice.exception;

public class UsernameNotFoundException extends org.springframework.security.core.userdetails.UsernameNotFoundException {
    private Integer code;
    public UsernameNotFoundException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
