package hotel.service;

import hotel.controller.dto.CreateRoomRequest;
import hotel.controller.dto.UpdateRoomRequest;
import hotel.model.Room;
import hotel.model.RoomCategory;
import hotel.model.RoomStatus;
import hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository repository){
        this.roomRepository = repository;
    }

    public long createRoom(CreateRoomRequest request) {
        long RoomNumber = request.getRoomNumber();
        long guestNumber = request.getGuestsNumber();
        RoomCategory category = request.getCategory();
        double price = request.getPrice();

        Room Room = new Room(RoomNumber, guestNumber, category, RoomStatus.AVAILABLE, price);

        return roomRepository.save(Room).getId();
    }

    public Room editRoomStatus(long roomId, RoomStatus status) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.setStatus(status);
            room = roomRepository.save(room);
        }
        return room;
    }

    public Room editRoomInfo(long roomId, UpdateRoomRequest request) {
        Room room = findRoomById(roomId);
        if (room != null) {
            room.setRoomNumber(request.getRoomNumber());
            room.setGuestsNumber(request.getGuestsNumber());
            room.setCategory(request.getCategory());
            room.setStatus(request.getStatus());
            room.setPrice(request.getPrice());
            room = roomRepository.save(room);
        }
        return room;
    }

    public Room findRoomById(long id) {
        return roomRepository.findById(id);
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> findRoomsByStatus(RoomStatus status) {
        return roomRepository.findAllByStatus(status);
    }

    public List<Room> findAvailableRoomsByGuestsNumberAndCategory(long guestsNumber, RoomCategory category) {
        return roomRepository.findAllByGuestsNumberAndStatusAndCategory(RoomStatus.AVAILABLE, guestsNumber, category);
    }

}
