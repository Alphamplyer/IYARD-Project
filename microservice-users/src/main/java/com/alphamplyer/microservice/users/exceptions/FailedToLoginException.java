package com.alphamplyer.microservice.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * To throw when login failed
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class FailedToLoginException extends RuntimeException {
    public FailedToLoginException(String message) {
        super(message);
    }
}
