package com.back.funnote.users;

import com.back.funnote.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationUser {

    @Id
    private String id;
    private int sequence;
    private String username;
    private String password;
    private String email;
    private Collection<Role> roles = new ArrayList<>();

}
