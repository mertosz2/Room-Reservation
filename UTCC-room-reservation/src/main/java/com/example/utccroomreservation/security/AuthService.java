package com.example.utccroomreservation.security;

import com.example.utccroomreservation.student.*;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final StudentsRepository studentsRepository;
    private final AuthenticationManager authenticationManager;



    public AuthService(JwtService jwtService, StudentsRepository studentsRepository, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.studentsRepository = studentsRepository;
        this.authenticationManager = authenticationManager;
    }


    public String authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        Students students = studentsRepository.findByStudentNumber(request.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("student not found")
        );

        return jwtService.generateToken(students);
    }
}
