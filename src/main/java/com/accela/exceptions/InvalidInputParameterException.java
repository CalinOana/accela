package com.accela.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidInputParameterException extends BaseException {

    public InvalidInputParameterException(final ErrorKey errorKey, final String... params) {
        super(errorKey, params);
    }
}
