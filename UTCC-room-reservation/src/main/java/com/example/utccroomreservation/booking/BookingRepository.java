package com.example.utccroomreservation.booking;

import com.example.utccroomreservation.rooms.Rooms;
import com.example.utccroomreservation.student.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingByStudents(Students students);

    @Query("SELECT COUNT(b) > 0 from Booking b WHERE b.date = :date AND ((b.startTime BETWEEN :startTime AND :endTime)) AND b.rooms = :rooms ")
    Boolean isBookingAlreadyBook(@Param("date") LocalDate date, @Param("startTime") LocalTime startTime, @Param("endTime") LocalTime endTime, @Param("rooms")Rooms rooms);

    List<Booking> findBookingByRooms(Rooms rooms);
}
