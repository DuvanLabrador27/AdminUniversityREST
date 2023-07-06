package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;

public interface TeacherService {
    public TeacherDTO getTeacherForId(Long id);
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO, Long id);
    public void deleteTeacher(Long id);
}
