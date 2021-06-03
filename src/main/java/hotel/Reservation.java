package hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
@NoArgsConstructor
public class Reservation {

    @Id
    @Getter @Setter
    @Column(columnDefinition = "BIGINT", nullable = false)
    private long id;

    @Getter @Setter
    @Column(columnDefinition = "BIGINT", nullable = false)
    private long roomId;

    @Getter @Setter
    @Column(columnDefinition = "BIGINT", nullable = false)
    private long userId;

    @Getter @Setter
    @Column(columnDefinition = "DECIMAL", nullable = false)
    private double price;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Getter @Setter
    @Column(columnDefinition = "VARCHAR", nullable = false)
    private Timestamp startDate;

    public Reservation(long roomId, long userId, double price, ReservationStatus status, Timestamp startDate) {
        this.roomId = roomId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
    }
}
