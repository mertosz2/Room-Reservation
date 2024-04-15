package com.example.utccroomreservation.student;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {

    @NotEmpty(message = "studentNumber can not be null or empty")
    @Pattern(regexp = "^($|[0-9]{13})")
    private Long studentNumber;

    @NotEmpty(message = "password can not be null or empty")
    @Size(min = 6)
    private String password;

    @NotEmpty(message = "name can not be null or empty")
    @Size(min = 4)
    private String name;

    @NotEmpty(message = "phone can not be null or empty")
    @Pattern(regexp = "^($|[0-9]{10})", message =  "phone number must be 10 digit")
    private String phone;

    @NotEmpty(message = "email can not be null or empty")
    @Email(message = "email example 2xxxxxxxxxx@live4.utcc.ac.th")
    private String email;
}
