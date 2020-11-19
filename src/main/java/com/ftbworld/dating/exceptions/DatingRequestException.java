package com.ftbworld.dating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use this when the request has not enough/bad body data.
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DatingRequestException extends RuntimeException {
    public DatingRequestException(String message) {
        super(message);
    }
}
