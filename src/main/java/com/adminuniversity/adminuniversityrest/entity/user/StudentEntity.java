package com.adminuniversity.adminuniversityrest.entity.user;

import com.adminuniversity.adminuniversityrest.entity.CourseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentEntity extends User {

    @ManyToMany
    @JoinTable(name = "student_course")
    private List<CourseEntity> courses;
}