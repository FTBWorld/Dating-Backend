package com.ftbworld.dating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use this when a resource is not found.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DatingNotFoundException extends RuntimeException {
    public DatingNotFoundException(String message) {
        super(message);
    }
}
