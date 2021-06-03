package hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rooms")
@NoArgsConstructor
public class Room {

    @Id
    @Column(columnDefinition = "BIGINT")
    @Getter @Setter
    private long id;

    @Getter @Setter
    @Column(columnDefinition = "BIGINT")
    private long roomNumber;

    @Getter @Setter
    @Column(columnDefinition = "BIGINT")
    private long guestNumber;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Getter @Setter
    @Column(columnDefinition = "DECIMAL")
    private double price;

    public Room(long roomNumber, long guestNumber, RoomCategory category, RoomStatus status, double price) {
        this.roomNumber = roomNumber;
        this.guestNumber = guestNumber;
        this.category = category;
        this.status = status;
        this.price = price;
    }
}
