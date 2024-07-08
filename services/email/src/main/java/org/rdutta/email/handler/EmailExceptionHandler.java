package org.rdutta.email.handler;

import org.rdutta.email.exception.EmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailExceptionHandler {
    @ExceptionHandler(EmailException.class)
    public ResponseEntity<ErrorResponse> handleEmailException(EmailException e) {
        System.out.println("Check the properties added to json, that does not exist on type 'microsoft.graph.message'. Make sure to only use property names that are defined by the type or mark the type as open type.");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
