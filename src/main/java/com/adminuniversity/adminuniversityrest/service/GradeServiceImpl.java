package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.GradeDTO;
import com.adminuniversity.adminuniversityrest.entity.GradeEntity;
import com.adminuniversity.adminuniversityrest.repository.CourseRepository;
import com.adminuniversity.adminuniversityrest.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public GradeEntity mapEntity(GradeDTO gradeDTO, long studentId, long courseId) {
        GradeEntity gradeEntity = new GradeEntity();
        gradeEntity.setId(gradeDTO.getId());
        gradeEntity.setScore(gradeEntity.getScore());
        gradeEntity.setCourse(courseRepository.findById(studentId).orElseThrow());
        gradeEntity.setStudent(studentRepository.findById(studentId).orElseThrow());

        return gradeEntity;
    }

    @Override
    public GradeDTO mapDTO(GradeEntity gradeEntity) {
        GradeDTO gradeDTO = new GradeDTO();
        gradeDTO.setId(gradeEntity.getId());
        gradeDTO.setScore(gradeDTO.getScore());

        return gradeDTO;
    }
}
