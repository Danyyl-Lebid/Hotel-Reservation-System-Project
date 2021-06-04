package hotel.repository;

import hotel.model.Reservation;
import hotel.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByUserId(long userId);

    List<Reservation> findAllByStatus(ReservationStatus status);

    List<Reservation> findByStatusAndStartDateLessThan(ReservationStatus status, Timestamp twoDaysAgo);

    Reservation findOrderById(long orderId);

}
