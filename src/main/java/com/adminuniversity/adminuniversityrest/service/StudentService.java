package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;

public interface StudentService {
    public StudentDTO getStudentForId(Long id);
    public StudentDTO createStudent(StudentDTO studentDTO);
    public StudentDTO updateStudent(StudentDTO studentDTO, Long id);
    public void deleteStudent(Long id);


}
