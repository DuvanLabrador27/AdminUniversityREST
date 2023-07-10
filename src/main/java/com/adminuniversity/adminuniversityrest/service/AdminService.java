package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.AdminDTO;
import com.adminuniversity.adminuniversityrest.exception.UserWithSameUsernameAlreadyExists;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface AdminService {
    AdminDTO getAdminById(Long id);
    AdminDTO getAdminByUsername(String username);
    AdminDTO createAdmin(AdminDTO adminDTO) throws UserWithSameUsernameAlreadyExists;
    AdminDTO updateAdmin(AdminDTO adminDTO);
    void deleteAdmin(Long id);

    void sendStudentsReport(String destination) throws UnirestException;
    void sendTeachersReport(String destination) throws UnirestException;
}
