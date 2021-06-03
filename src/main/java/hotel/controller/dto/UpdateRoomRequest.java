package hotel.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import hotel.model.RoomCategory;
import hotel.model.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@JsonDeserialize
public class UpdateRoomRequest {

    private long roomNumber;

    private long guestsNumber;

    private RoomCategory category;

    private RoomStatus status;

    private double price;

}
