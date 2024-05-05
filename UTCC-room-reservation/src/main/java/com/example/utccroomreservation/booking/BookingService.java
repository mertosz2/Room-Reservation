package com.example.utccroomreservation.booking;

import com.example.utccroomreservation.exception.RoomNotFoundException;
import com.example.utccroomreservation.exception.RoomsAlreadyBookedException;
import com.example.utccroomreservation.exception.StudentNotFoundException;
import com.example.utccroomreservation.rooms.Rooms;
import com.example.utccroomreservation.rooms.RoomsRepository;
import com.example.utccroomreservation.student.Students;
import com.example.utccroomreservation.student.StudentsRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomsRepository roomsRepository;

    private final StudentsRepository studentsRepository;

    public BookingService(BookingRepository bookingRepository, RoomsRepository roomsRepository, StudentsRepository studentsRepository) {
        this.bookingRepository = bookingRepository;
        this.roomsRepository = roomsRepository;
        this.studentsRepository = studentsRepository;
    }

    @Transactional
    public BookingResponse bookingRoom(BookingRequest bookingRequest){

        Long id = 100000000L + new Random().nextInt(900000000);
        Rooms rooms = roomsRepository.findByRoomNumber(bookingRequest.getRoomNumber()).orElseThrow(
                () -> new RoomNotFoundException("Room not found with given room number " + bookingRequest.getRoomNumber())
        );
        Students students = studentsRepository.findByStudentNumber(bookingRequest.getStudentNumber()).orElseThrow(
                () -> new StudentNotFoundException("Student not found with given id " + bookingRequest.getStudentNumber())
        );

        Boolean check = bookingRepository.isBookingAlreadyBook(
                bookingRequest.getDate(),
                bookingRequest.getStartTime(),
                bookingRequest.getEndTime(),
                rooms
        );
        if(check){
            throw new RoomsAlreadyBookedException("This room already booked at " + bookingRequest.getStartTime() + "-" + bookingRequest.getEndTime());
        }

        Booking newBooking = Booking.builder()
                .id(id)
                .startTime(bookingRequest.getStartTime())
                .endTime(bookingRequest.getEndTime())
                .date(bookingRequest.getDate())
                .timestamp(LocalDateTime.now())
                .status(true)
                .rooms(rooms)
                .students(students)
                .build();
        bookingRepository.save(newBooking);
        return mapToBookingResponse(newBooking, "booking successfully", bookingRequest.getRoomNumber());

    }

    public List<Booking> findBookingByRooms(Long roomNumber){
        Rooms rooms = roomsRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new RoomNotFoundException("Room not found with given room number " + roomNumber)
        );
        return bookingRepository.findBookingByRooms(rooms);
    }

    public List<Booking> getAllBookingByStudentId(String studentNumber){
        Students students = studentsRepository.findByStudentNumber(studentNumber).orElseThrow(
                () -> new StudentNotFoundException("Student not found with given id " + studentNumber)
        );
        List<Booking> bookingList = bookingRepository.findBookingByStudents(students);
        return bookingList;
    }

    public BookingResponse mapToBookingResponse(Booking booking, String msg, Long roomNumber){
        BookingResponse bookingResponse = BookingResponse.builder()
                .status(booking.isStatus())
                .date(booking.getDate())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .roomNumber(roomNumber)
                .msg(msg)
                .build();
        return  bookingResponse;
    }
}
