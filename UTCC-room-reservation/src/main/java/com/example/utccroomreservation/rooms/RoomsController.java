package com.example.utccroomreservation.rooms;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/room")
@Validated
public class RoomsController {

    private final RoomsService roomsService;

    public RoomsController(RoomsService roomsService) {
        this.roomsService = roomsService;
    }

    @PostMapping
    public ResponseEntity<String> createRooms(@Valid @RequestBody RoomsRequest  roomsRequest){
        return ResponseEntity.status(CREATED).body(
                roomsService.createRooms(roomsRequest)
        );
    }

    @GetMapping("/building/{buildingNumber}")
    public ResponseEntity<List<Rooms>> getRoomsByBuildingNumber(@PathVariable int buildingNumber){
        return ResponseEntity.status(OK).body(
                roomsService.getRoomsFromBuilding(buildingNumber)
        );
    }

    @GetMapping("{roomNumber}")
    public ResponseEntity<Rooms> getRoomsByRoomNumber(@PathVariable Long roomNumber){
        return ResponseEntity.status(OK).body(
                roomsService.getRoomByRoomNumber(roomNumber)
        );
    }
}
