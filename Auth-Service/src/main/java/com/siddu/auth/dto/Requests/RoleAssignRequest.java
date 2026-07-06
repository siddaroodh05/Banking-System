package com.siddu.auth.dto.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class RoleAssignRequest {
    @Email
    @NotBlank(message = "provide email")
    private String email;
    @NotBlank(message ="provide rolename")
    private String roles;

}
