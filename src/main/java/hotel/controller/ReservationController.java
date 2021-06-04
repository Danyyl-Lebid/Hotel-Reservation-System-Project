package hotel.controller;


import hotel.controller.dto.CreateReservationRequest;
import hotel.model.Reservation;
import hotel.model.ReservationStatus;
import hotel.model.RoomStatus;
import hotel.service.PermissionService;
import hotel.service.ReservationService;
import hotel.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final PermissionService permissionService;
    private final RoomService roomService;

    public ReservationController(ReservationService reservationService, PermissionService permissionService, RoomService roomService) {
        this.reservationService = reservationService;
        this.permissionService = permissionService;
        this.roomService = roomService;
    }

    @PostMapping
    public long placeReservation(@RequestBody CreateReservationRequest request) {
        return reservationService.placeReservation(request);
    }

    @PutMapping("/status/confirmed/{userId}/{ReservationId}")
    public Reservation confirmReservation(@PathVariable long userId, @PathVariable long ReservationId) {
        permissionService.checkPermission(userId);
        Reservation reservation = reservationService.updateOrderStatus(ReservationId, ReservationStatus.CONFIRMED);
        roomService.editRoomStatus(reservation.getRoomId(), RoomStatus.BOOKED);
        return reservation;
    }

    @PutMapping("/status/payed/{userId}/{ReservationId}")
    public Reservation markReservationPayed(@PathVariable long userId, @PathVariable long ReservationId) {
        permissionService.checkPermission(userId);
        Reservation reservation = reservationService.updateOrderStatus(ReservationId, ReservationStatus.PAID);
        roomService.editRoomStatus(reservation.getRoomId(), RoomStatus.BUSY);
        return reservation;
    }

    @PutMapping("/status/cancel/{ReservationId}/{userId}")
    public Reservation cancelReservation(@PathVariable long userId, @PathVariable long ReservationId) {
        permissionService.checkPermission(userId);
        Reservation reservation = reservationService.updateOrderStatus(ReservationId, ReservationStatus.CANCELLED);
        roomService.editRoomStatus(reservation.getRoomId(), RoomStatus.AVAILABLE);
        return reservation;
    }

    @GetMapping("/userid/{userId}")
    public List<Reservation> findReservationsByUserId(@PathVariable long userId) {
        return reservationService.findOrdersByUserId(userId);
    }

    @GetMapping("/id/{id}")
    public Reservation findReservationById(@PathVariable long id) {
        return reservationService.findOrderById(id);
    }

    @GetMapping("/status/{status}/{userId}")
    public List<Reservation> findReservationsByReservationStatus(@PathVariable long userId, @PathVariable ReservationStatus status) {
        permissionService.checkPermission(userId);
        return reservationService.findOrdersByOrderStatus(status);
    }

    @GetMapping("/{userId}")
    public List<Reservation> findAllReservations(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        return reservationService.findAllOrders();
    }

    @GetMapping("/expired/{userId}")
    public List<Reservation> findNoPayedExpiredReservations(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        return reservationService.findNoPayedExpiredOrders();
    }

    @PutMapping("/cancel/{userId}")
    public List<Reservation> cancelNoPayedReservations(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        List<Reservation> expiredReservations = findNoPayedExpiredReservations(userId);
        for (Reservation reservation : expiredReservations) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            roomService.editRoomStatus(reservation.getRoomId(), RoomStatus.AVAILABLE);
        }
        return expiredReservations;
    }

}
