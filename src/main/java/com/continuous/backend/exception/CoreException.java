package com.continuous.backend.exception;

import org.springframework.http.HttpStatus;

public abstract class CoreException extends RuntimeException {

    private final CoreErrorType errorType;

    protected CoreException(CoreErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public String getErrorCode() {
        return errorType.getErrorCode();
    }

    public String getMessage() {
        return errorType.getMessage();
    }

    public HttpStatus getStatus() {
        return errorType.getStatus();
    }
}
