package com.leis.bear.exception;

public class UnAuthorizedException extends CommonException {
    public UnAuthorizedException(String message) {
        super(message, 401);
    }

    public UnAuthorizedException(String message, Throwable cause) {
        super(message, cause, 401);
    }

    public UnAuthorizedException(Throwable cause) {
        super(cause, 401);
    }

}
