package com.example.utccroomreservation.security;


import com.example.utccroomreservation.student.Students;
import com.example.utccroomreservation.student.StudentsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    private final StudentsRepository studentsRepository;


    public AuthenticationService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }


    @Override
    public Students loadUserByUsername(String studentNumber) {
        return studentsRepository.findByStudentNumber(studentNumber).orElseThrow(
                () -> new UsernameNotFoundException("student not found")
        );
    }
}
