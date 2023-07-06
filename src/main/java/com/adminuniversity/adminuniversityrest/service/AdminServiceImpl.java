package com.adminuniversity.adminuniversityrest.service;

import com.adminuniversity.adminuniversityrest.dto.entity.user.AdminDTO;
import com.adminuniversity.adminuniversityrest.entity.user.AdminEntity;
import com.adminuniversity.adminuniversityrest.exception.UserWithSameUsernameAlreadyExists;
import com.adminuniversity.adminuniversityrest.repository.AdminRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Lazy
    private final PasswordEncoder passwordEncoder;

    private final AdminRepository adminRepository;

    public AdminServiceImpl(PasswordEncoder passwordEncoder, AdminRepository adminRepository) {
        this.passwordEncoder = passwordEncoder;
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminDTO getAdminById(Long id) {
        return mapToDTO(adminRepository.findById(id).orElseThrow());
    }

    @Override
    public AdminDTO createAdmin(AdminDTO adminDTO) throws UserWithSameUsernameAlreadyExists {
        if (adminRepository.findByUsername(adminDTO.getUsername()).isPresent())
            throw new UserWithSameUsernameAlreadyExists(adminDTO.getUsername());

        return mapToDTO(adminRepository.save(mapToEntity(adminDTO)));
    }

    @Override
    public AdminDTO updateAdmin(AdminDTO adminDTO) {
        return mapToDTO(adminRepository.save(mapToEntity(adminDTO)));
    }

    @Override
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    private AdminDTO mapToDTO(AdminEntity admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(adminDTO.getId());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setFirstName(admin.getFirstName());
        adminDTO.setLastName(admin.getLastName());

        adminDTO.setUsername(admin.getUsername());
        adminDTO.setPassword(admin.getPassword());

        return adminDTO;
    }

    private AdminEntity mapToEntity(AdminDTO adminDTO) {
        AdminEntity admin = new AdminEntity();
        admin.setId(adminDTO.getId());
        admin.setEmail(adminDTO.getEmail());
        admin.setFirstName(adminDTO.getFirstName());
        admin.setLastName(adminDTO.getLastName());

        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));

        return admin;
    }
}
