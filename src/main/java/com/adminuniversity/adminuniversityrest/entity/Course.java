package com.adminuniversity.adminuniversityrest.entity;

import com.adminuniversity.adminuniversityrest.entity.user.Student;
import com.adminuniversity.adminuniversityrest.entity.user.Teacher;
import com.adminuniversity.adminuniversityrest.entity.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    private Set<Grade> grades;
}