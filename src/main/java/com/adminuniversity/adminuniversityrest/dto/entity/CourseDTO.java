package com.adminuniversity.adminuniversityrest.dto.entity;

import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class CourseDTO {

    private Long id;
    private TeacherDTO teacher;
    private List<StudentDTO> students;
    private Map<Long, Set<GradeDTO>> grades;

}
