package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.AdminDTO;
import com.adminuniversity.adminuniversityrest.dto.entity.user.StudentDTO;
import com.adminuniversity.adminuniversityrest.exception.UserWithSameUsernameAlreadyExists;

public interface AdminService {
    AdminDTO getAdminById(Long id);
    AdminDTO createAdmin(AdminDTO adminDTO) throws UserWithSameUsernameAlreadyExists;
    AdminDTO updateAdmin(AdminDTO adminDTO);
    void deleteAdmin(Long id);
}
