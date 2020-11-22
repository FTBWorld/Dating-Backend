package com.ftbworld.dating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use this when the request has authorization.
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DatingAuthException extends RuntimeException {
    public DatingAuthException(String message) {
        super(message);
    }
}
