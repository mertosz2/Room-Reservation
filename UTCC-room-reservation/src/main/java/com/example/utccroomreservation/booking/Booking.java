package com.example.utccroomreservation.booking;

import com.example.utccroomreservation.rooms.Rooms;
import com.example.utccroomreservation.student.Students;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    @Id
    private Long id;

    private LocalDateTime timestamp;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    private boolean status ;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private Students students;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Rooms rooms;


}
