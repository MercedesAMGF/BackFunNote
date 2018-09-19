package com.back.funnote.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

@Data @AllArgsConstructor @NoArgsConstructor
public class Role {

    @Id
    private String id;
    //private int sequence;
    private String roleName;
}
