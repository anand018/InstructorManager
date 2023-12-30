package com.simlearn.instructormanager.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {
    private String username;
    private String assignToEmail;
    private String courseName;
    private String courseCode;
    private String licenses;
    private int groupOfFive;
    private int groupOfFour;
    private String startTime;
    private String endTime;
    private String attempts;
}