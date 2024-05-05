package com.example.utccroomreservation.rooms;

import com.example.utccroomreservation.booking.Booking;
import com.example.utccroomreservation.exception.RoomNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RoomsService {

    private final RoomsRepository roomsRepository;

    public RoomsService(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }



    public String createRooms(RoomsRequest roomsRequest){
        Long id = 100000000L + new Random().nextInt(900000000);
        Rooms rooms = Rooms.builder()
                .roomId(id)
                .roomNumber(roomsRequest.getRoomNumber())
                .building(roomsRequest.getBuilding())
                .floor(roomsRequest.getFloor())
                .capacity(roomsRequest.getCapacity())
                .facilities(roomsRequest.getFacilities())
                .imgUrl(roomsRequest.getImgUrl())
                .build();
        roomsRepository.save(rooms);

        return "created !";
    }



    public List<Rooms> getRoomsFromBuilding(int buildingNumber){
        List<Rooms> roomsList = roomsRepository.findRoomsByBuilding(buildingNumber);
        return roomsList;
    }

    public Rooms getRoomByRoomNumber(Long roomNumber){
        return roomsRepository.findByRoomNumber(roomNumber).orElseThrow(
                () -> new RoomNotFoundException("Room not found with given number")
        );
    }

}
