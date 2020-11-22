package com.ftbworld.dating.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Use this when the actor has not enough permissions, i.e. doing something they shouldn't be.
@ResponseStatus(HttpStatus.FORBIDDEN)
public class DatingPermissionsException extends RuntimeException {
    public DatingPermissionsException(String message) {
        super(message);
    }
}
