package hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class Room {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private long roomNumber;

    @Getter @Setter
    private long guestNumber;

    @Getter @Setter
    private RoomCategory category;

    @Getter @Setter
    private RoomStatus status;

    @Getter @Setter
    private double price;

    public Room(long roomNumber, long guestNumber, RoomCategory category, RoomStatus status, double price) {
        this.roomNumber = roomNumber;
        this.guestNumber = guestNumber;
        this.category = category;
        this.status = status;
        this.price = price;
    }
}
