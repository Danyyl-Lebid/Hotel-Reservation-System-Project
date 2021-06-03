package hotel.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "rooms")
@NoArgsConstructor
public class Room {

    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @NotNull
    @Column(name = "apartment_number", nullable = false, columnDefinition = "BIGINT(20)")
    private long roomNumber;

    @NotNull
    @Column(name = "guests_number", nullable = false, columnDefinition = "BIGINT(20)")
    private long guestsNumber;

    @NotNull
    @Column(name = "category", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private RoomCategory category;

    @NotNull
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @Getter @Setter
    @Column(columnDefinition = "DECIMAL")
    private double price;

    public Room(long roomNumber, long guestsNumber, RoomCategory category, RoomStatus status, double price) {
        this.roomNumber = roomNumber;
        this.guestsNumber = guestsNumber;
        this.category = category;
        this.status = status;
        this.price = price;
    }
}
