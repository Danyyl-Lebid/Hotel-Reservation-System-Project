package hotel.service;

import hotel.controller.dto.CreateReservationRequest;
import hotel.model.Reservation;
import hotel.model.ReservationStatus;
import hotel.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public long placeReservation(CreateReservationRequest request) {
        long roomId = request.getRoomId();
        long userId = request.getUserId();
        double price = request.getPrice();

        Reservation order = new Reservation(roomId, userId, price, ReservationStatus.CREATED, Timestamp.from(Instant.now()));

        return reservationRepository.save(order).getId();
    }

    public Reservation updateOrderStatus(long orderId, ReservationStatus orderStatus) {
        Reservation order = findOrderById(orderId);
        order.setStatus(orderStatus);
        return reservationRepository.save(order);
    }

    public Reservation findOrderById(long orderId) {
        return reservationRepository.findOrderById(orderId);
    }

    public List<Reservation> findOrdersByUserId(long userId) {
        return reservationRepository.findAllByUserId(userId);
    }

    public List<Reservation> findOrdersByOrderStatus(ReservationStatus status) {
        return reservationRepository.findAllByStatus(status);
    }

    public List<Reservation> findAllOrders() {
        return reservationRepository.findAll();
    }

    public List<Reservation> findNoPayedExpiredOrders() {
        Timestamp twoDaysAgo = Timestamp.from(Instant.now().minusSeconds(2*24*60*60));
        return reservationRepository.findByStatusAndStartDateLessThan(ReservationStatus.CONFIRMED, twoDaysAgo);
    }



}
