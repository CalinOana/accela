package com.accela.web.advice;

import com.accela.exceptions.BaseException;
import com.accela.exceptions.InvalidInputParameterException;
import com.accela.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@RequestMapping(produces = "application/json")
@ResponseBody
@Slf4j
public class AccelaControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> defaultExceptionHandlerMethod(final Exception e) {
        return error(INTERNAL_SERVER_ERROR, new BaseException(e));
    }

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(final ResourceNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler(value = InvalidInputParameterException.class)
    public ResponseEntity<ApiError> handleInvalidInputParameterException(final InvalidInputParameterException e) {
        return error(BAD_REQUEST, e);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<ApiError> handleHttpClientErrorException(final HttpClientErrorException e) {
        return error(HttpStatus.valueOf(e.getRawStatusCode()), e);
    }


    private ResponseEntity<ApiError> error(final HttpStatus status, final BaseException e) {
        log.error("Exception Key is: {}", e.getErrorKey(), e);
        return getApiErrorResponseEntity(status, e);
    }

    private ResponseEntity<ApiError> error(final HttpStatus status, final RestClientResponseException e) {
        log.error("HTTP Exception Code is: {}", e.getRawStatusCode(), e);
        return getApiErrorResponseEntity(status, e);
    }

    private ResponseEntity<ApiError> getApiErrorResponseEntity(final HttpStatus status, final BaseException e) {
        final String errorMessage = e.getMessage() == null ? e.getErrorKey().getKey() : "";
        final ApiError apiError = buildApiError(status, e, errorMessage);
        return new ResponseEntity<>(apiError, status);
    }

    private ResponseEntity<ApiError> getApiErrorResponseEntity(final HttpStatus status, final RestClientResponseException e) {
        final ApiError apiError = new ApiError(status, e.getMessage());
        return new ResponseEntity<>(apiError, status);
    }

    private ApiError buildApiError(final HttpStatus status, final BaseException e, final String errorMessage) {
        final String errorKey = e == null || e.getErrorKey() == null ? null : e.getErrorKey().getKey();
        return new ApiError(status, errorMessage, errorKey);
    }

}
