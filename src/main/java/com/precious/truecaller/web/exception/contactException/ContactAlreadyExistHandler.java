package com.precious.truecaller.web.exception.contactException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ContactAlreadyExistHandler {

    public ResponseEntity<Object> handleContactAlreadyExist(ContactAlreadyExistsException ex){
        ContactException exp = new ContactException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
    }
}
