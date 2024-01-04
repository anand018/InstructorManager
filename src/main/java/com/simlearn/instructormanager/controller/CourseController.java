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

    @PostMapping("/course/archive/{archive}/{instructorEmail}/{courseCode}")
    public void updateCourseArchive(@PathVariable boolean archive, @PathVariable String instructorEmail, @PathVariable String courseCode) {
        courseService.updateCourseArchive(archive, instructorEmail, courseCode);
    }

    @PostMapping("/course/attempts/{attempts}/{instructorEmail}/{courseCode}")
    public void updateCourseArchive(@PathVariable int attempts, @PathVariable String instructorEmail, @PathVariable String courseCode) {
        courseService.updateCourseAttempts(attempts, instructorEmail, courseCode);
    }

    @PostMapping("/course/schedule/{startTime}/{endTime}/{instructorEmail}/{courseCode}")
    public void updateCourseSchedule(@PathVariable String startTime, @PathVariable String endTime, @PathVariable String instructorEmail, @PathVariable String courseCode) {
        courseService.updateCourseSchedule(startTime, endTime, instructorEmail, courseCode);
    }
}