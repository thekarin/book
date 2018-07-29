package com.ooa1769.bs.support.security;

public class UnAuthenticationException extends Exception {

    public UnAuthenticationException(String message) {
        super(message);
    }

    public UnAuthenticationException(String message, Throwable ex) {
        super(message, ex);
    }
}
