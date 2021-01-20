package com.accela.exceptions;

import lombok.Data;

@Data
public class ErrorKey {

    private String key;

    private ErrorKey(final String key) {
        this.key = key;
    }

    public static ErrorKey withKey(final String key) {
        return new ErrorKey(key);
    }
}
