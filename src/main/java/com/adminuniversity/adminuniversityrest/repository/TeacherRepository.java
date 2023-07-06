package com.adminuniversity.adminuniversityrest.repository;

import com.adminuniversity.adminuniversityrest.entity.user.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {

}
