package com.adminuniversity.adminuniversityrest.entity.user;

import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity extends User {

    @OneToMany(mappedBy = "teacher")
    private List<CourseEntity> courses;

}