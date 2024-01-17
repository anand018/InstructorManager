package com.simlearn.instructormanager.dto;

import lombok.Data;

@Data
public class StudentDto {
    String fullName;
    String email;
    boolean isAttempted;
}
