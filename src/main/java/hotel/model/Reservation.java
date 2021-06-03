package hotel.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "reservations")
@NoArgsConstructor
public class Reservation {

    @Id
    @Setter(value = AccessLevel.NONE)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT(20)")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @NotNull
    @Column(name = "apartment_id", nullable = false, columnDefinition = "BIGINT(20)")
    private long roomId;

    @NotNull
    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT(20)")
    private long userId;

    @NotNull
    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10,8)")
    private double price;

    @NotNull
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(32)")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @NotNull
    @Column(name = "start_date", nullable = false, columnDefinition = "TIMESTAMP")
    private Timestamp startDate;

    public Reservation(long roomId, long userId, double price, ReservationStatus status, Timestamp startDate) {
        this.roomId = roomId;
        this.userId = userId;
        this.price = price;
        this.status = status;
        this.startDate = startDate;
    }
}
