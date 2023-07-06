package com.adminuniversity.adminuniversityrest.controller;

import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentForId(@PathVariable Long id){
        try {
            StudentDTO studentDTO = this.studentService.getStudentForId(id);
            return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<StudentDTO>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO){
        try {
            StudentDTO student = this.studentService.createStudent(studentDTO);
            return new ResponseEntity<StudentDTO>(student,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<StudentDTO>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO, @PathVariable Long id){
        try{
            StudentDTO student = this.studentService.updateStudent(studentDTO,id);
            return new ResponseEntity<StudentDTO>(student,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<StudentDTO>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        try {
            this.studentService.deleteStudent(id);
            return new ResponseEntity<>("The student has been deleted correctly ",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
