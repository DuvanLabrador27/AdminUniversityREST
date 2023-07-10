package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.entity.user.StudentEntity;

import java.util.List;

public interface StudentService {
    public StudentDTO getStudentForId(Long id);
    public StudentDTO createStudent(StudentDTO studentDTO);
    public StudentDTO updateStudent(StudentDTO studentDTO, Long id);
    public void deleteStudent(Long id);
    void subscribeCourse(Long studentId, Long courseId);
    StudentEntity mapEntity(StudentDTO studentDTO);
    StudentDTO mapDTO(StudentEntity studentEntity);
    List<StudentEntity> getAllStudents();
    void unsubscribeCourse(Long studentId, Long courseId);

}
