package com.adminuniversity.adminuniversityrest.entity.user;

import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity extends User {

    @OneToMany(mappedBy = "teacher")
    private List<CourseEntity> courses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton((GrantedAuthority) () -> "teacher");
    }
}