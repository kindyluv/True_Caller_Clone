package com.precious.truecaller.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotificationException extends RuntimeException {

    public NotificationException(String message){
        super(message);
    }
    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }
}
