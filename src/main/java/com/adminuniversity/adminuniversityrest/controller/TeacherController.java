package com.adminuniversity.adminuniversityrest.controller;
import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import com.adminuniversity.adminuniversityrest.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getTeacherForId(@PathVariable Long id){
        try{
            TeacherDTO teacher = teacherService.getTeacherForId(id);
            return new ResponseEntity<TeacherDTO>(teacher,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<TeacherDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> updateTeacher(@RequestBody TeacherDTO teacherDTO, @PathVariable Long id){
      try {
          TeacherDTO teacher = this.teacherService.updateTeacher(teacherDTO,id);
          return new ResponseEntity<TeacherDTO>(teacher,HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<TeacherDTO>(HttpStatus.CONFLICT);
      }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id){
        try {
            this.teacherService.deleteTeacher(id);
            return new ResponseEntity<>("The Teacher has been delete correctly", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
