package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.CourseDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.GradeDTO;
import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface CourseService {
    public CourseDTO getCourseById(Long id);
    CourseEntity mapEntity(CourseDTO courseDTO);
    CourseDTO mapDTO(CourseEntity courseEntity);
    ResponseEntity<CourseEntity> setTeacher(Long courseId, Long teacherId);
    ResponseEntity<CourseEntity> removeTeacher(Long courseId,  Long teacherId);
    List<CourseDTO> getAllCourses();
    ResponseEntity<CourseEntity> addStudent(Long courseId, Long studentId);
    ResponseEntity<CourseEntity> deleteStudent(Long courseId, Long studentId);

    Set<GradeDTO> getStudentGrades(Long courseId, Long studentId);
    Set<GradeDTO> addStudentGrade(Long courseId, Long StudentId, float grade);
    double getStudentAverage(Long courseId, Long studentId);

    CourseDTO createCourse(CourseDTO courseDTO);
    CourseDTO updateCourse(CourseDTO courseDTO);
    ResponseEntity<Object> deleteCourse(Long id);
}
