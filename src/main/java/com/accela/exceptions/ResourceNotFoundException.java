package com.accela.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends BaseException {

    public ResourceNotFoundException(final ErrorKey errorKey, final String... params) {
        super(errorKey, params);
    }

}
