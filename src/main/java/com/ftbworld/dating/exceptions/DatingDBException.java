package com.ftbworld.dating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use this when the database has an issue.
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DatingDBException extends RuntimeException {
    public DatingDBException(String message) {
        super(message);
    }
}
