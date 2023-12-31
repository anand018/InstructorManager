package com.simlearn.instructormanager.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException (String message) {
        super(message);
    }
}
