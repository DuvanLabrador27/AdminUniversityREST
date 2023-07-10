package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import com.adminuniversity.adminuniversityrest.entity.user.TeacherEntity;

import java.util.List;

public interface TeacherService {
    public List<TeacherDTO> getAllTeachers();
    public TeacherDTO getTeacherForId(Long id);
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO, Long id);
    public void deleteTeacher(Long id);
    public TeacherEntity mapToEntity(TeacherDTO teacherDTO);
    public TeacherDTO mapToDTO(TeacherEntity teacherEntity);
}
