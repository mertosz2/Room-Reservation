package com.example.utccroomreservation.security;

import com.example.utccroomreservation.exception.StudentNotFoundException;
import com.example.utccroomreservation.student.*;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final StudentsRepository studentsRepository;
    private final AuthenticationManager authenticationManager;

    private final StudentsService studentsService;



    public AuthService(JwtService jwtService, StudentsRepository studentsRepository, AuthenticationManager authenticationManager, StudentsService studentsService) {
        this.jwtService = jwtService;
        this.studentsRepository = studentsRepository;
        this.authenticationManager = authenticationManager;
        this.studentsService = studentsService;
    }


    public StudentResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Students students = studentsRepository.findByStudentNumber(request.getUsername()).orElseThrow(
                () -> new StudentNotFoundException("student not found")
        );

        return studentsService.mapToStudentResponse(students, jwtService.generateToken(students));
    }
}
