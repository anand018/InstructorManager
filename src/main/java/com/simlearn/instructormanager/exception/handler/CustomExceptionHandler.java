package com.simlearn.instructormanager.exception.handler;


import com.simlearn.instructormanager.error.ErrorObject;
import com.simlearn.instructormanager.exception.CourseNotFoundException;
import com.simlearn.instructormanager.exception.EnrollmentException;
import com.simlearn.instructormanager.exception.UnknownDBException;
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
    @ExceptionHandler(EnrollmentException.class)
    public ResponseEntity<ErrorObject> handleEnrollmentException(EnrollmentException ae) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("02");
        errorObject.setMessage(ae.getMessage());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UnknownDBException.class)
    public ResponseEntity<ErrorObject> handleUnknownDBException(UnknownDBException ae) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setError("100");
        errorObject.setMessage(ae.getMessage());
        return new ResponseEntity<>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
