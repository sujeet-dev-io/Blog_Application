package com.blog.files.exception;

import java.io.Serial;

public class NotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public String defaultMessage;
    public NotFoundException(String message) {
        super(message);
        this.defaultMessage = message;
    }
}
