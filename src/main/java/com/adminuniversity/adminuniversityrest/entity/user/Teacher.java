package com.adminuniversity.adminuniversityrest.entity.user;

import com.adminuniversity.adminuniversityrest.entity.Course;
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
public class Teacher extends User {

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

}