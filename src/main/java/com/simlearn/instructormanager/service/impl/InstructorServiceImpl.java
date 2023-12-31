package com.simlearn.instructormanager.service.impl;

import com.simlearn.instructormanager.dto.InstructorDto;
import com.simlearn.instructormanager.entity.InstructorEntity;
import com.simlearn.instructormanager.exception.CourseNotFoundException;
import com.simlearn.instructormanager.mapper.InstructorMapper;
import com.simlearn.instructormanager.service.InstructorService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private InstructorMapper instructorMapper;

    @Override
    public void saveCourse(InstructorDto instructorDto) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(instructorDto.getAssignToEmail()));
        InstructorEntity instructorEntity = mongoTemplate.findOne(query, InstructorEntity.class);

        if (ObjectUtils.isEmpty(instructorEntity)) {
            instructorEntity = new InstructorEntity();
            instructorEntity.setEmail(instructorDto.getAssignToEmail());
            instructorEntity.setUsername(instructorDto.getUsername());
            mongoTemplate.save(instructorMapper.updateInstructorEntity(instructorEntity, instructorDto));
        } else {
            Update update = new Update().set("courseEntities", instructorMapper.updateInstructorEntity(instructorEntity, instructorDto).getCourseEntities());
            mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(instructorEntity.get_id())), update, InstructorEntity.class);
        }
    }

    @Override
    public InstructorEntity getCourses(String username) {
        InstructorEntity instructorEntity = mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), InstructorEntity.class);
        if (ObjectUtils.isEmpty(instructorEntity))
            throw new CourseNotFoundException("No course found");
        return instructorEntity;
    }
}