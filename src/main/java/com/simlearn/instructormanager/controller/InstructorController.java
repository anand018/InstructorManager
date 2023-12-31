package com.simlearn.instructormanager.controller;

import com.simlearn.instructormanager.dto.InstructorDto;
import com.simlearn.instructormanager.entity.InstructorEntity;
import com.simlearn.instructormanager.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/course")
    public void saveCourse(@RequestBody InstructorDto instructor) {
        instructorService.saveCourse(instructor);
    }

    @GetMapping("/courses/{username}")
    public InstructorEntity getCourses(@PathVariable String username) {
        return instructorService.getCourses(username);
    }
}
