package com.abc.api.exception;

import java.util.Date;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.abc.api.exception.errorDetails.ErrorDetails;

@ControllerAdvice
public class ExceptionHandlerApi {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> primaryKeyException(Exception e) {
    	ErrorDetails error = new ErrorDetails();
    	error.setMessage(e.getMessage());
    	error.setTimeStamp(new Date());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);

    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> invalidInput(Exception e) {
ErrorDetails error = new ErrorDetails();
error.setMessage(e.getMessage());
error.setTimeStamp(new Date());
        return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);

    }
}
