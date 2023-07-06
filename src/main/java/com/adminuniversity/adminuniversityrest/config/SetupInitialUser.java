package com.adminuniversity.adminuniversityrest.config;

import com.adminuniversity.adminuniversityrest.dto.entity.user.AdminDTO;
import com.adminuniversity.adminuniversityrest.exception.UserWithSameUsernameAlreadyExists;
import com.adminuniversity.adminuniversityrest.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SetupInitialUser implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final AdminService adminService;

    public SetupInitialUser(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        if (alreadySetup) return;

        AdminDTO adminDTO = new AdminDTO(null, "test@test.com", "Admin", "Admin", "admin", "admin");
        try {
            adminService.createAdmin(adminDTO);
        } catch (UserWithSameUsernameAlreadyExists ignored) {}

        alreadySetup = true;
    }

}
