package com.adminuniversity.adminuniversityrest.repository;

import com.adminuniversity.adminuniversityrest.dto.entity.CourseDTO;
import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
