package com.simlearn.instructormanager.service;

import com.simlearn.instructormanager.entity.CourseEntity;

public interface CourseService {
    CourseEntity findCourse(String courseCode);
}
