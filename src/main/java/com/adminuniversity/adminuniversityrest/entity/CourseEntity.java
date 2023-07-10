package com.adminuniversity.adminuniversityrest.entity;

import com.adminuniversity.adminuniversityrest.entity.user.StudentEntity;
import com.adminuniversity.adminuniversityrest.entity.user.TeacherEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToMany(mappedBy = "courses")
    private List<StudentEntity> students = new ArrayList<StudentEntity>();

    @OneToMany(mappedBy = "course")
    private Set<GradeEntity> grades = new HashSet<>();
}