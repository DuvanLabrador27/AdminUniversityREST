package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.AdminDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.entity.user.StudentEntity;
import com.adminuniversity.adminuniversityrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        //Convert of DTO to ENTITIES
        StudentEntity studentToEntity = new StudentEntity();
        studentToEntity.setEmail(studentDTO.getEmail());
        studentToEntity.setFirstName(studentDTO.getFirstName());
        studentToEntity.setLastName(studentDTO.getLastName());

        StudentEntity newStudent = this.studentRepository.save(studentToEntity);

        //Convert of ENTITIES to DTO
        StudentDTO studentToDTO = new StudentDTO();
        studentToDTO.setId(newStudent.getId());
        studentToDTO.setEmail(newStudent.getEmail());
        studentToDTO.setFirstName(newStudent.getFirstName());
        studentToDTO.setLastName(newStudent.getLastName());

        return studentToDTO;
    }
}
