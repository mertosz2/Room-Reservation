package com.example.utccroomreservation.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity<String> createStudents(@RequestBody StudentRequest studentRequest){
        return ResponseEntity.status(CREATED).body(
                studentsService.createStudent(studentRequest)
        );
    }
}
