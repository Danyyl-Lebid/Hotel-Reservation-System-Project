package hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@NoArgsConstructor
public class Reservation {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private long roomId;

    @Getter @Setter
    private long userId;

    @Getter @Setter
    private double price;

    @Getter @Setter
    private ReservationStatus status;

    @Getter @Setter
    private Timestamp startDate;

    public Reservation(long roomId, long userId, double price, ReservationStatus status, Timestamp startDate) {
        this.roomId = roomId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
    }
}
