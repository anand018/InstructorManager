package com.simlearn.instructormanager.mapper;

import com.simlearn.instructormanager.dto.InstructorDto;
import com.simlearn.instructormanager.entity.CourseEntity;
import com.simlearn.instructormanager.entity.GroupFiveEntity;
import com.simlearn.instructormanager.entity.GroupFourEntity;
import com.simlearn.instructormanager.entity.InstructorEntity;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class InstructorMapper {

    public InstructorEntity updateInstructorEntity(InstructorEntity instructorEntity, InstructorDto instructorDto) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseCode(instructorDto.getCourseCode());
        courseEntity.setCourseName(instructorDto.getCourseName());
        courseEntity.setAttempts(instructorDto.getAttempts());
        courseEntity.setStartTime(instructorDto.getStartTime());
        courseEntity.setEndTime(instructorDto.getEndTime());
        courseEntity.setLicenses(instructorDto.getLicenses());
        courseEntity.setGroupFiveEntityList(createGroupFiveEntity(instructorDto.getGroupOfFive(), new ArrayList<GroupFiveEntity>()));
        courseEntity.setGroupFourEntityList(createGroupFourEntity(instructorDto.getGroupOfFour(), new ArrayList<GroupFourEntity>()));
        List<CourseEntity> courseEntitiesList = new ArrayList<>();
        courseEntitiesList.add(courseEntity);
        if (instructorEntity.getCourseEntities().isEmpty()) {
            instructorEntity.setCourseEntities(courseEntitiesList);
        } else {
            List<CourseEntity> courseEntities = instructorEntity.getCourseEntities();
            courseEntities.add(courseEntity);
            instructorEntity.setCourseEntities(courseEntities);
        }
        return instructorEntity;
    }

    private List<GroupFiveEntity> createGroupFiveEntity(int numberOfGroups, List<GroupFiveEntity> groupFiveEntities) {
        while (numberOfGroups > 0) {
            GroupFiveEntity groupFiveEntity = new GroupFiveEntity();
            groupFiveEntity.setGroupCode(Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(), Character.MAX_RADIX).substring(0, 10));
            groupFiveEntity.setLimit(5);
            groupFiveEntities.add(groupFiveEntity);
            numberOfGroups--;
        }
        return groupFiveEntities;
    }

    private List<GroupFourEntity> createGroupFourEntity(int numberOfGroups, List<GroupFourEntity> groupFourEntities) {
        while (numberOfGroups > 0) {
            GroupFourEntity groupFourEntity = new GroupFourEntity();
            groupFourEntity.setGroupCode(Long.toString(ByteBuffer.wrap(UUID.randomUUID().toString().getBytes()).getLong(), Character.MAX_RADIX).substring(0, 10));
            groupFourEntity.setLimit(4);
            groupFourEntities.add(groupFourEntity);
            numberOfGroups--;
        }
        return groupFourEntities;
    }
}
