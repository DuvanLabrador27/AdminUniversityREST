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
    public StudentDTO getStudentForId(Long id) {
        StudentEntity student = this.studentRepository.findById(id).orElseThrow();
        return mapDTO(student);
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        StudentEntity studentEntity = mapEntity(studentDTO);
        studentEntity.setUsername(studentDTO.getUsername());
        studentEntity.setPassword(studentDTO.getPassword());
        StudentEntity newStudent = this.studentRepository.save(studentEntity);
        StudentDTO student = mapDTO(newStudent);
        return student;
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO, Long id) {
        StudentEntity student = this.studentRepository.findById(id).orElseThrow();
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setUsername(studentDTO.getUsername());
        student.setPassword(studentDTO.getPassword());
        StudentEntity updateStudent = studentRepository.save(student);
        return mapDTO(updateStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        StudentEntity student = this.studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);
    }

    private StudentEntity mapEntity(StudentDTO studentDTO){
        StudentEntity student = new StudentEntity();
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        return student;
    }

    private StudentDTO mapDTO(StudentEntity studentEntity){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setEmail(studentEntity.getEmail());
        studentDTO.setFirstName(studentEntity.getFirstName());
        studentDTO.setLastName(studentEntity.getLastName());

        return studentDTO;
    }
}
