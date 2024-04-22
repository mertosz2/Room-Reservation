package com.example.utccroomreservation.rooms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomsRequest {

    @NotNull(message = "RoomNumber can not be null or empty")
    private Long roomNumber;

    @NotNull(message = "building can not be null or empty")
    private int building;

    @NotNull(message = "floor can not be null or empty")
    private int floor;

    @NotNull(message = "capacity can not be null or empty")
    private int capacity;

    @NotEmpty(message = "facilities can not be null or empty")
    private String facilities;

    @NotEmpty(message = "imgUrl can not be null or empty")
    private String imgUrl;
}
