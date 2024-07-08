package org.rdutta.student.handler;

import org.rdutta.student.exception.StudentNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ExceptionHandler(StudentNotFound.class)
    public ResponseEntity<String> handleStudentNotFound(StudentNotFound studentNotFound) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(studentNotFound.getMessage());
    }
}
