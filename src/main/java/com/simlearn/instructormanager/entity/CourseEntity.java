package com.simlearn.instructormanager.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Document("Course")
public class CourseEntity {
    private String courseName;
    private String courseCode;
    private String gameId;
    private int licenses;
    private String startTime;
    private String endTime;
    private String attempts;
    private boolean archive;
    private List <GroupFiveEntity> groupFiveEntityList;
    private List <GroupFourEntity> groupFourEntityList;
    private List<String> enrolledStudentsList;
    private LocalDate createdDate;
}