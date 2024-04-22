package com.example.utccroomreservation.rooms;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rooms {

    @Id
    private Long roomId;

    private Long roomNumber;

    private int building;

    private int floor;

    private int capacity;

    private String facilities;

    private String imgUrl;

}
