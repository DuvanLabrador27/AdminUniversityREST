package com.adminuniversity.adminuniversityrest.dto.entity.user;

import lombok.Data;

@Data
public class UserDTO {
    protected Long id;
    protected String email;
    protected String firstName;
    protected String lastName;
}
