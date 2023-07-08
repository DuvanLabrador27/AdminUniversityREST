package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.TeacherDTO;
import com.adminuniversity.adminuniversityrest.entity.user.TeacherEntity;
import com.adminuniversity.adminuniversityrest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDTO getTeacherForId(Long id) {
        TeacherEntity teacherEntity = this.teacherRepository.findById(id).orElseThrow();
        return mapToDTO(teacherEntity);
    }

    @Override
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO, Long id) {
        TeacherEntity teacherEntity = this.teacherRepository.findById(id).orElseThrow();
        teacherEntity.setEmail(teacherDTO.getEmail());
        teacherEntity.setFirstName(teacherDTO.getFirstName());
        teacherEntity.setLastName(teacherDTO.getLastName());
        teacherEntity.setPassword(teacherDTO.getPassword());
        teacherEntity.setUsername(teacherDTO.getUsername());
        TeacherEntity newTeacher = this.teacherRepository.save(teacherEntity);
        return  mapToDTO(newTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        TeacherEntity teacherEntity = this.teacherRepository.findById(id).orElseThrow();
        this.teacherRepository.delete(teacherEntity);
    }

    private TeacherEntity mapToEntity(TeacherDTO teacherDTO){
        TeacherEntity teacher = new TeacherEntity();
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        return teacher;
    }

    private TeacherDTO mapToDTO(TeacherEntity teacherEntity){
        TeacherDTO teacher = new TeacherDTO();
        teacher.setId(teacherEntity.getId());
        teacher.setEmail(teacherEntity.getEmail());
        teacher.setFirstName(teacherEntity.getFirstName());
        teacher.setLastName(teacherEntity.getLastName());

        return teacher;
    }
}
