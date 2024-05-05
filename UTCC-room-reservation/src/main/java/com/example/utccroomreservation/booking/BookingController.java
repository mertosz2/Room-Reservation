package com.example.utccroomreservation.booking;

import com.example.utccroomreservation.student.StudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;


    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;

    }

    @GetMapping("/{roomNumber}")
    public ResponseEntity<List<Booking>> findBookingByRooms(@PathVariable Long roomNumber){
        return ResponseEntity.status(200).body(
                bookingService.findBookingByRooms(roomNumber)
        );
    }



    @PostMapping
    public ResponseEntity<BookingResponse> roomBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.status(CREATED).body(
                bookingService.bookingRoom(bookingRequest)
        );
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookingByStudents(){
        String studentNumber = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.status(OK).body(
                bookingService.getAllBookingByStudentId(studentNumber)
        );

    }

}
