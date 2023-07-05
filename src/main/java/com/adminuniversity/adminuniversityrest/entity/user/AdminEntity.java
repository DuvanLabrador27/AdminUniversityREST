package com.adminuniversity.adminuniversityrest.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class AdminEntity extends User {
}
