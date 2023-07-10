package com.adminuniversity.adminuniversityrest.controller;

import com.adminuniversity.adminuniversityrest.dto.entity.CourseDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import com.adminuniversity.adminuniversityrest.service.CourseService;
import com.adminuniversity.adminuniversityrest.service.StudentService;
import com.adminuniversity.adminuniversityrest.service.TeacherService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity addStudent(@PathVariable Long courseId, @PathVariable Long studentId){
        try{
            courseService.addStudent(courseId, studentId);
            return new ResponseEntity(HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/{courseId}/student/{studentId}/delete")
    public ResponseEntity deleteStudent(@PathVariable Long courseId, @PathVariable Long studentId){
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
