package com.example.utccroomreservation.student;

import com.example.utccroomreservation.security.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final PasswordEncoder encoder;

    public StudentsService(StudentsRepository studentsRepository, PasswordEncoder encoder) {
        this.studentsRepository = studentsRepository;
        this.encoder = encoder;
    }

    public String createStudent(StudentRequest studentRequest){
        Long randomId = 100000000L + new Random().nextInt(900000000);
        Students students = Students.builder()
                .studentId(randomId)
                .studentNumber(studentRequest.getStudentNumber())
                .name(studentRequest.getName())
                .password(encoder.encode(studentRequest.getPassword()))
                .email(studentRequest.getEmail())
                .phone(studentRequest.getPhone())
                .roles(Collections.singleton(Role.STUDENT))
                .permissions(Collections.singleton(Permission.STUDENT_READ))
                .faculty(studentRequest.getFaculty())
                .major(studentRequest.getMajor())
                .build();

        studentsRepository.save(students);
        return "created !";

    }

    public String createAdmin(StudentRequest studentRequest){
        Long randomId = 100000000L + new Random().nextInt(900000000);
        Students students = Students.builder()
                .studentId(randomId)
                .studentNumber(studentRequest.getStudentNumber())
                .name(studentRequest.getName())
                .password(encoder.encode(studentRequest.getPassword()))
                .email(studentRequest.getEmail())
                .phone(studentRequest.getPhone())
                .roles(Collections.singleton(Role.ADMIN))
                .permissions(Collections.singleton(Permission.ADMIN_PERMISSION))
                .faculty(studentRequest.getFaculty())
                .major(studentRequest.getMajor())
                .build();

        studentsRepository.save(students);
        return "created !";

    }

    public Students findByNumber(String studentNumber){
        return studentsRepository.findByStudentNumber(studentNumber).orElseThrow(
                () -> new UsernameNotFoundException("student not found")
        );
    }

    public StudentResponse mapToStudentResponse(Students students, String token){
        StudentResponse studentResponse = StudentResponse.builder()
                .studentId(students.getStudentId())
                .studentNumber(students.getStudentNumber())
                .email(students.getEmail())
                .password(students.getPassword())
                .name(students.getName())
                .phone(students.getPhone())
                .faculty(students.getFaculty())
                .major(students.getMajor())
                .roles(students.getRoles())
                .permissions(students.getPermissions())
                .token(token)
                .build();
        return studentResponse;
    }

}
