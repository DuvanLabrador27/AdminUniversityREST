package com.adminuniversity.adminuniversityrest.dto.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
}
