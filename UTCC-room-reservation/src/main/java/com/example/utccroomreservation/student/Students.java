package com.example.utccroomreservation.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Students {

    @Id
    private Long studentId;

    private Long studentNumber;

    private String password;

    private String name;
}

