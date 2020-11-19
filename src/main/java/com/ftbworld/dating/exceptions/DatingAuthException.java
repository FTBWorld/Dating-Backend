package com.ftbworld.dating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DatingAuthException extends RuntimeException {
    public DatingAuthException(String message) {
        super(message);
    }
}
