package com.adminuniversity.adminuniversityrest.repository;

import com.adminuniversity.adminuniversityrest.entity.user.AdminEntity;
import com.adminuniversity.adminuniversityrest.service.UserDetailsServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    Optional<UserDetails> findByUsername(String username);
}