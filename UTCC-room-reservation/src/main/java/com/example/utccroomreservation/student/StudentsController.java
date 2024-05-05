package com.example.utccroomreservation.student;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/students")
@Validated
public class StudentsController {

    private final StudentsService studentsService;

    public StudentsController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PostMapping
    public ResponseEntity<String> createStudents(@Valid @RequestBody StudentRequest studentRequest){
        return ResponseEntity.status(CREATED).body(
                studentsService.createStudent(studentRequest)
        );
    }

    @PostMapping("/admin")
    public ResponseEntity<String> createAdmin(@Valid @RequestBody StudentRequest studentRequest){
        return ResponseEntity.status(CREATED).body(
                studentsService.createAdmin(studentRequest)
        );
    }

    @GetMapping("/test/{studentNumber}")
    public ResponseEntity<Students> findByNumber(@PathVariable String studentNumber){
        return ResponseEntity.ok().body(
                studentsService.findByNumber(studentNumber)
        );
    }
}
