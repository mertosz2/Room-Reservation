package com.example.utccroomreservation.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    Optional<Students> findByStudentNumber(String studentNumber);


}
