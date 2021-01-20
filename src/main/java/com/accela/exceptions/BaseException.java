package com.accela.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {

    private String message;
    private ErrorKey errorKey;
    private String[] params;

    public BaseException(final Throwable throwable) {
        super(throwable);
    }

    public BaseException(final String message) {
        this.message = message;
    }

    public BaseException(final String message, final ErrorKey errorKey) {
        this.message = message;
        this.errorKey = errorKey;
    }

    public BaseException(final ErrorKey errorKey, final String... params) {
        this.errorKey = errorKey;
        this.params = params;
    }

    public BaseException(final String message, final ErrorKey errorKey, final String... params) {
        this.message = message;
        this.errorKey = errorKey;
        this.params = params;
    }

}
