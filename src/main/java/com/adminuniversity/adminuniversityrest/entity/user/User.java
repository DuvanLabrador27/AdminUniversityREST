package com.adminuniversity.adminuniversityrest.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@MappedSuperclass
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String firstName;

    @Column(nullable = false)
    protected String lastName;

    protected String username;

    protected String password;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // endregion
}