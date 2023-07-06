package com.adminuniversity.adminuniversityrest.dto.entity.user;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserDTO {
    protected Long id;
    protected String email;
    protected String firstName;
    protected String lastName;

    protected String username;
    protected String password;
}
