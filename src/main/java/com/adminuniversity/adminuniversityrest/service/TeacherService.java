package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;

import java.util.List;

public interface TeacherService {
    public List<TeacherDTO> getAllTeachers();
    public TeacherDTO getTeacherForId(Long id);
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO, Long id);
    public void deleteTeacher(Long id);
}
