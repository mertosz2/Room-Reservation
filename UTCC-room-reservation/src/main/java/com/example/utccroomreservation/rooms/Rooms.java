package com.example.utccroomreservation.rooms;

import com.example.utccroomreservation.booking.Booking;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @JsonIgnore
    @OneToMany(mappedBy = "rooms" )
    private List<Booking> bookingList;

}
