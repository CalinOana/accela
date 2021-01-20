package com.accela.web.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
public class ApiError implements Serializable {

    private HttpStatus status;
    private String message;
    private String errorKey;

    public ApiError(final HttpStatus status, final String message, final String errorKey) {
        this.status = status;
        this.message = message;
        this.errorKey = errorKey;
    }

    public ApiError(final HttpStatus status, final String message) {
        this.status = status;
        this.message = message;
    }

}
