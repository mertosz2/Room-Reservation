package com.example.utccroomreservation.student;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StudentsService {

    private final StudensRepository studensRepository;

    public StudentsService(StudensRepository studensRepository) {
        this.studensRepository = studensRepository;
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
                .build();

        studensRepository.save(students);
        return "created !";

    }
}
