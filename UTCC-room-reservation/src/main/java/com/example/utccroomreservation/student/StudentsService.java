package com.example.utccroomreservation.student;

import com.example.utccroomreservation.security.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public String createStudent(StudentRequest studentRequest){
        Long randomId = 100000000L + new Random().nextInt(900000000);
        Students students = Students.builder()
                .studentId(randomId)
                .studentNumber(studentRequest.getStudentNumber())
                .name(studentRequest.getName())
                .password(studentRequest.getPassword())
                .email(studentRequest.getEmail())
                .phone(studentRequest.getPhone())
                .role(Role.STUDENT)
                .build();

        studentsRepository.save(students);
        return "created !";

    }

    public Students findByNumber(String studentNumber){
        return studentsRepository.findByStudentNumber(studentNumber).orElseThrow(
                () -> new UsernameNotFoundException("student not found")
        );
    }
}
