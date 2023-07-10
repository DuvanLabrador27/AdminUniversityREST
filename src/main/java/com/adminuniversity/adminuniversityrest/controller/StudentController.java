package com.adminuniversity.adminuniversityrest.controller;

import com.adminuniversity.adminuniversityrest.dto.entity.CourseDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.entity.user.StudentEntity;
import com.adminuniversity.adminuniversityrest.service.CourseService;
import com.adminuniversity.adminuniversityrest.service.StudentService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    private final CourseService courseService;
    @Autowired
    public StudentController(StudentService studentService, CourseService courseService){
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<StudentEntity>> getAllStudents(){
        List<StudentEntity> students = studentService.getAllStudents();
        return new ResponseEntity<>(
                students, HttpStatus.OK
        );
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

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> generateStudentReport(@PathVariable Long id){
        try {
            StudentDTO student = this.studentService.getStudentForId(id);
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Student report");
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLine();

            int yOffset = 20;
            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.showText("ID: " + student.getId());
            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.showText("First Name: " + student.getFirstName());
            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.showText("Last Name: " + student.getLastName());
            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.showText("Email: " + student.getEmail());
            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.newLineAtOffset(0, -yOffset);
            if(student.getCourses() != null){
                contentStream.showText("Courses subscribed: ");
                contentStream.newLineAtOffset(0, -yOffset);
                for(CourseDTO courses : student.getCourses()){
                    contentStream.showText("Course name: " + courses.getName());
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("ID: " + courses.getId());
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("Teacher: " + courses.getTeacher());
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("Grades: " + courses.getGrades());
                    contentStream.newLineAtOffset(0, -yOffset);
                }
            } else {
                contentStream.showText("This student is not subscribed in any course");
                contentStream.newLineAtOffset(0, -yOffset);
            }

            contentStream.newLineAtOffset(0, -yOffset);
            contentStream.newLine();

            contentStream.endText();
            contentStream.close();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            document.save(outputStream);
            document.close();

            byte[] pdfBytes = outputStream.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("student_report.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
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

    @PostMapping(path = "{studentId}/course/{courseId}/subscribe")
    public ResponseEntity<Object> subscribeCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        try {
            studentService.subscribeCourse(studentId, courseId);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(
                HttpStatus.ACCEPTED
        );
    }

    @DeleteMapping(path = "{studentId}/course/{courseId}/unsubscribe")
    public ResponseEntity<Object> unsubscribeCourse(@PathVariable Long studentId, @PathVariable Long courseId){
        try {
            studentService.unsubscribeCourse(studentId, courseId);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>(
                HttpStatus.ACCEPTED
        );
    }

}
