package com.example.utccroomreservation.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test/{studentNumber}")
    public ResponseEntity<Students> findByNumber(@PathVariable String studentNumber){
        return ResponseEntity.ok().body(
                studentsService.findByNumber(studentNumber)
        );
    }
}
