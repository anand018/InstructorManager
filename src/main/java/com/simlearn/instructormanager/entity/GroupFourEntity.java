package com.simlearn.instructormanager.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@ToString
@Document("GroupFive")
public class GroupFourEntity {
    private int limit;
    private String groupCode;
    private List<StudentEntity> students;
}