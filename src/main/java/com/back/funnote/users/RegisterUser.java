package com.back.funnote.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    private String id;
    private String username;
    private String email;
    private String password;
    private String repassword;
    private String role;
}
