package com.siddu.auth.dto.Response;


import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class UserStatusResponse {

    private String email;
    private String status;
    private boolean isEmailVerified;
    private List<String> roles;
}
