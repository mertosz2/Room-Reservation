package com.example.utccroomreservation.rooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomsRepository extends JpaRepository<Rooms, Long> {

    List<Rooms> findRoomsByBuilding(int building);

    Optional<Rooms> findByRoomNumber(Long roomNumber);
}
