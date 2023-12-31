package com.simlearn.instructormanager.exception.handler;


import com.simlearn.instructormanager.error.ErrorObject;
import com.simlearn.instructormanager.exception.CourseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorObject> handleAuthenticationFailedException(CourseNotFoundException ae) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("01");
        errorObject.setMessage(ae.getMessage());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }
}
