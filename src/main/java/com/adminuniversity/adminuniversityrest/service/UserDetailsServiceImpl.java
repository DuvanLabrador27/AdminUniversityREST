package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.repository.AdminRepository;
import com.adminuniversity.adminuniversityrest.repository.StudentRepository;
import com.adminuniversity.adminuniversityrest.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AdminRepository adminRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    public UserDetailsServiceImpl(AdminRepository adminRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.adminRepository = adminRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  adminRepository.findByUsername(username)
                .or(() -> studentRepository.findByUsername(username))
                .or(() -> teacherRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));

    }
}
