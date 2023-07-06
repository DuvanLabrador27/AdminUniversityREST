package com.adminuniversity.adminuniversityrest.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "admins")
public class AdminEntity extends User {
    @SuppressWarnings("JpaAttributeTypeInspection")
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> "admin");
    }
}
