package org.deco.gachicoding.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    protected ApplicationException(String errorCode, HttpStatus httpStatus, String message) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
