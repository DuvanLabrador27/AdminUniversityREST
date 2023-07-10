package com.adminuniversity.adminuniversityrest.controller;
import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import com.adminuniversity.adminuniversityrest.service.TeacherService;
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
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers(){
        return this.teacherService.getAllTeachers();
    }

    @PostMapping
    public ResponseEntity<?> registerTeacher(@RequestBody TeacherDTO teacherDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacherDTO));
        } catch (IllegalArgumentException ignored) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateTeacherReport(){
        try {
            List<TeacherDTO> teachers = teacherService.getAllTeachers();

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Teacher Report");
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLine();

            int yOffset = 20;
            contentStream.newLineAtOffset(0, -yOffset);
            if(!teachers.isEmpty()){
                for (TeacherDTO teacher : teachers) {
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("ID: " + teacher.getId());
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("First Name: " + teacher.getFirstName());
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("Last Name: " + teacher.getLastName());
                    contentStream.newLineAtOffset(0, -yOffset);
                    contentStream.showText("Email: " + teacher.getEmail());
                    contentStream.newLineAtOffset(0, -yOffset);

                }
            } else {
                contentStream.showText("There isn't record of any teacher");
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
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename("teacher_report.pdf").build());

            return ResponseEntity.ok().headers(headers).body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
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
