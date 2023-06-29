package com.adminuniversity.adminuniversityrest.dto.entity.user;

import com.adminuniversity.adminuniversityrest.dto.entity.CourseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDTO extends UserDTO {
    private List<CourseDTO> courses;
}
