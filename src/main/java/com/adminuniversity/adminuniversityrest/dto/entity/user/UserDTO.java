package com.adminuniversity.adminuniversityrest.dto.entity.user;

import lombok.Data;

@Data
public abstract class UserDTO {
    protected Long id;
    protected String email;
    protected String firstName;
    protected String lastName;
}
