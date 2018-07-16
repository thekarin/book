package com.ooa1769.bs.support.security;

public class UnAuthorizedException extends RuntimeException {

    public UnAuthorizedException(String message) {
        super(message);
    }

    public UnAuthorizedException(String message, Throwable ex) {
        super(message, ex);
    }
}

