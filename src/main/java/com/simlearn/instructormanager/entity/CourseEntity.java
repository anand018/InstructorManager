package com.simlearn.instructormanager.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Document("Course")
public class CourseEntity {
    @Id
    private String id;
    private String courseName;
    private String courseCode;
    private String licenses;
    private String startTime;
    private String endTime;
    private String attempts;
    private List <GroupFiveEntity> groupFiveEntityList;
    private List <GroupFourEntity> groupFourEntityList;
    private List<String> enrolledParticipants = new ArrayList<>();
}
