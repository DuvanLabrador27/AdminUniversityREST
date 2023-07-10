package com.adminuniversity.adminuniversityrest.controller;

import com.adminuniversity.adminuniversityrest.dto.entity.CourseDTO;
import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import com.adminuniversity.adminuniversityrest.service.CourseService;
import com.adminuniversity.adminuniversityrest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;
    @Autowired
    public CourseController(CourseService courseService, StudentService studentService){
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<CourseEntity>> getAllCourses(){
        List<CourseEntity> courses = courseService.getAllCourses();
        return new ResponseEntity<>(
                courses, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseForId(@PathVariable Long id){
        try {
            CourseDTO courseDTO = this.courseService.getCourseById(id);
            return new ResponseEntity<CourseDTO>(courseDTO, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<CourseDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/{courseId}/student/{studentId}/add")
    public ResponseEntity<?> addStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        try{
            courseService.addStudent(courseId, studentId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());

        }
    }

    @PostMapping("/{courseId}/{studentId}/grade")
    public ResponseEntity<?> addGrade(@PathVariable Long courseId, @PathVariable Long studentId, @RequestBody String grade) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addStudentGrade(courseId, studentId, Float.parseFloat(grade)));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/{courseId}/{studentId}/grade")
    public ResponseEntity<?> getStudentGrades(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.getStudentGrades(courseId, studentId));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("/{courseId}/{studentId}/grade/average")
    public ResponseEntity<?> getStudentAverage(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(courseService.getStudentAverage(courseId, studentId));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @DeleteMapping(path = "/{courseId}/student/{studentId}/delete")
    public ResponseEntity<?> deleteStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        try{
            courseService.deleteStudent(courseId, studentId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping(path = "/{courseId}/teacher/{teacherId}/set")
    public ResponseEntity setTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        try{
            courseService.setTeacher(courseId, teacherId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{courseId}/teacher/{teacherId}/remove")
    public ResponseEntity removeTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        try{
            courseService.removeTeacher(courseId, teacherId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        try {
            CourseDTO course = this.courseService.createCourse(courseDTO);
            return new ResponseEntity<>(course,HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO, @PathVariable Long id){
        try{
            CourseDTO course = this.courseService.updateCourse(courseDTO);
            return new ResponseEntity<CourseDTO>(course,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<CourseDTO>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Long id){
        try {
            ResponseEntity<Object> course = this.courseService.deleteCourse(id);
            return new ResponseEntity<>(course,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
