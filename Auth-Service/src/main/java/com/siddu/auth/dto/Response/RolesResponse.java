package com.siddu.auth.dto.Response;

import com.siddu.auth.Enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RolesResponse {
    private String message;
    private String Assignedrole;

}
