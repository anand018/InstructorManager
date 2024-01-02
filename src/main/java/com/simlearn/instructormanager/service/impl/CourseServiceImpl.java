package com.simlearn.instructormanager.service.impl;

import com.simlearn.instructormanager.constants.GroupTypeEnum;
import com.simlearn.instructormanager.dto.StudentDto;
import com.simlearn.instructormanager.entity.CourseEntity;
import com.simlearn.instructormanager.entity.InstructorEntity;
import com.simlearn.instructormanager.entity.StudentEntity;
import com.simlearn.instructormanager.exception.CourseNotFoundException;
import com.simlearn.instructormanager.exception.EnrollmentException;
import com.simlearn.instructormanager.exception.UnknownDBException;
import com.simlearn.instructormanager.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

    public void updateAndAddStudent(String courseCode, String groupCode, StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmail(studentDto.getEmail());
        studentEntity.setFullName(studentDto.getFullName());

        InstructorEntity instructorEntity = mongoTemplate.findOne(new Query(Criteria.where("courseEntities.courseCode").is(courseCode)), InstructorEntity.class);
        Query query = new Query(Criteria.where("_id").is(instructorEntity.get_id())
                .and("courseEntities.courseCode").is(courseCode)
                .orOperator(
                        Criteria.where("courseEntities.groupFourEntityList.groupCode").is(groupCode),
                        Criteria.where("courseEntities.groupFiveEntityList.groupCode").is(groupCode)
                ));

        checkIfAlreadyEnrolled(query, studentEntity.getEmail());
        updateCourse(query, studentEntity, courseCode, groupCode);
    }

    private void updateCourse(Query query, StudentEntity studentEntity, String courseCode, String groupCode) {
        Update update = new Update();
        try {
            update.push("courseEntities.$[course].groupFourEntityList.$[group].students", studentEntity)
                    .push("courseEntities.$[course].groupFiveEntityList.$[group].students", studentEntity)
                    .inc("courseEntities.$[course].groupFourEntityList.$[group].limit", -1)
                    .inc("courseEntities.$[course].groupFiveEntityList.$[group].limit", -1)
                    .push("courseEntities.$[course].enrolledStudentsList", studentEntity.getEmail())
                    .inc("courseEntities.$[course].licenses", -1)
                    .filterArray(Criteria.where("course.courseCode").is(courseCode))
                    .filterArray(Criteria.where("group.groupCode").is(groupCode));

            mongoTemplate.updateFirst(query, update, InstructorEntity.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new UnknownDBException("Unknown DB error");
        }
    }

    private void checkIfAlreadyEnrolled(Query query, String email) {
        InstructorEntity instructor = mongoTemplate.findOne(query, InstructorEntity.class);
        if (ObjectUtils.isNotEmpty(instructor)
                && ObjectUtils.isNotEmpty(instructor.getCourseEntities())
                && !instructor.getCourseEntities().isEmpty()) {
            instructor.getCourseEntities().forEach(courseEntity -> {
                if (ObjectUtils.isNotEmpty(courseEntity)
                        && ObjectUtils.isNotEmpty(courseEntity.getEnrolledStudentsList())
                        && courseEntity.getEnrolledStudentsList().contains(email)) {
                    throw new EnrollmentException("Student is already enrolled in the course.");
                }
            });
        }
    }
}
