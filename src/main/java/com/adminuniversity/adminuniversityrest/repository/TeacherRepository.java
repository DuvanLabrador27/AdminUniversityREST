package com.adminuniversity.adminuniversityrest.repository;

import com.adminuniversity.adminuniversityrest.entity.user.AdminEntity;
import com.adminuniversity.adminuniversityrest.entity.user.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {
    Optional<UserDetails> findByUsername(String username);

}
