package com.example.utccroomreservation.student;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Students {

    @Id
    private Long studentId;

    private Long studentNumber;

    private String email;

    private String password;

    private String name;

    private String phone;
}

