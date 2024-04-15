package com.example.utccroomreservation.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudensRepository extends JpaRepository<Students, Long> {
}
