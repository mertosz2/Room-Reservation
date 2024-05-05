package com.example.utccroomreservation.student;

import com.example.utccroomreservation.security.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long studentId;

    private String studentNumber;

    private String email;

    private String password;

    private String name;

    private String phone;

    private String faculty;

    private String major;

    private Set<Role> roles;

    private Set<Permission> permissions;

    private String token;
}
