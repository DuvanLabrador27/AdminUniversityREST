package com.adminuniversity.adminuniversityrest.dto.entity;

import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import lombok.Data;

import java.util.*;

@Data
public class CourseDTO {

    private Long id;
    private String name;
    private TeacherDTO teacher;
    private List<StudentDTO> students = new ArrayList<StudentDTO>();
    private Map<Long, Set<GradeDTO>> grades = new HashMap<>();

}
