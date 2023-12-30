package com.simlearn.instructormanager.controller;

import com.simlearn.instructormanager.dto.InstructorDto;
import com.simlearn.instructormanager.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/create-course")
    public void saveInstructor(@RequestBody InstructorDto instructor) {
        instructorService.saveInstructor(instructor);
    }
}
