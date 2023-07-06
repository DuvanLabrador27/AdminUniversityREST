package com.adminuniversity.adminuniversityrest.dto.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class AdminDTO extends UserDTO {
    public AdminDTO(Long id, String email, String firstName, String lastName, String username, String password) {
        super(id, email, firstName, lastName, username, password);
    }
}
