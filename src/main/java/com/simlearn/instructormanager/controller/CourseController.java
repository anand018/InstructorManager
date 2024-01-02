package com.simlearn.instructormanager.controller;

import com.simlearn.instructormanager.constants.GroupTypeEnum;
import com.simlearn.instructormanager.dto.StudentDto;
import com.simlearn.instructormanager.entity.CourseEntity;
import com.simlearn.instructormanager.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/course/{courseCode}")
    CourseEntity findCourse(@PathVariable String courseCode) {
        return courseService.findCourse(courseCode);
    }
    @PostMapping("/join/{courseCode}/{groupCode}")
    void enrollStudentToCourse(@PathVariable String courseCode, @PathVariable String groupCode, @RequestBody StudentDto studentDto) {
        courseService.updateAndAddStudent(courseCode, groupCode, studentDto);
    }
}
