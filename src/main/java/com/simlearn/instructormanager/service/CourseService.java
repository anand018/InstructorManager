package com.simlearn.instructormanager.service;

import com.simlearn.instructormanager.constants.GroupTypeEnum;
import com.simlearn.instructormanager.dto.StudentDto;
import com.simlearn.instructormanager.entity.CourseEntity;
import com.simlearn.instructormanager.entity.StudentEntity;

public interface CourseService {
    CourseEntity findCourse(String courseCode);
    public void updateAndAddStudent(String courseCode, String groupCode, StudentDto studentDto);
}
