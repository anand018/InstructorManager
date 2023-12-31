package com.simlearn.instructormanager.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Document("Instructor")
public class InstructorEntity {
    @Id
    private String _id;
    private String email;
    private String username;
    private List<CourseEntity> courseEntities = new ArrayList<>();
}