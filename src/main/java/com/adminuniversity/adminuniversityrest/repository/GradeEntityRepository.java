package com.adminuniversity.adminuniversityrest.repository;

import com.adminuniversity.adminuniversityrest.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface GradeEntityRepository extends JpaRepository<GradeEntity, Long> {
    Set<GradeEntity> findByCourse_IdAndStudent_Id(Long courseId, Long studentId);

}