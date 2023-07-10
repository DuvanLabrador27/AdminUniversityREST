package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.AdminDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import com.adminuniversity.adminuniversityrest.entity.user.StudentEntity;
import com.adminuniversity.adminuniversityrest.repository.CourseRepository;
import com.adminuniversity.adminuniversityrest.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, CourseRepository courseRepository){
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
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

    @Override
    public void subscribeCourse(Long studentId, Long courseId){
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
            if(courseOptional.isPresent()){
                StudentEntity studentEntity = studentOptional.get();
                CourseEntity courseEntity = courseOptional.get();
                studentEntity.getCourses().add(courseEntity);
                courseEntity.getStudents().add(studentEntity);
                studentRepository.save(studentEntity);
                courseRepository.save(courseEntity);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    @Override
    public void unsubscribeCourse(Long studentId, Long courseId) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent()){
            Optional<CourseEntity> courseOptional = courseRepository.findById(courseId);
            if(courseOptional.isPresent()){
                StudentEntity studentEntity = studentOptional.get();
                CourseEntity courseEntity = courseOptional.get();
                studentEntity.getCourses().remove(courseEntity);
                courseEntity.getStudents().remove(studentEntity);
                studentRepository.save(studentEntity);
                courseRepository.save(courseEntity);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public StudentEntity mapEntity(StudentDTO studentDTO){
        StudentEntity student = new StudentEntity();
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());

        return student;
    }

    public StudentDTO mapDTO(StudentEntity studentEntity){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentEntity.getId());
        studentDTO.setEmail(studentEntity.getEmail());
        studentDTO.setFirstName(studentEntity.getFirstName());
        studentDTO.setLastName(studentEntity.getLastName());

        return studentDTO;
    }
}
