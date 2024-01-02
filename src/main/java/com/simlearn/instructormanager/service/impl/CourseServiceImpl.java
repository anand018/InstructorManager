package com.simlearn.instructormanager.service.impl;

import com.simlearn.instructormanager.entity.CourseEntity;
import com.simlearn.instructormanager.entity.InstructorEntity;
import com.simlearn.instructormanager.exception.CourseNotFoundException;
import com.simlearn.instructormanager.service.CourseService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public CourseEntity findCourse(String courseCode) {
        List<InstructorEntity> instructorEntityList = mongoTemplate.findAll(InstructorEntity.class);
        return instructorEntityList.stream()
                .flatMap(instructorEntity -> instructorEntity.getCourseEntities().stream())
                .filter(courseEntity -> courseEntity.getCourseCode().equals(courseCode))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("No course found"));
    }
}
