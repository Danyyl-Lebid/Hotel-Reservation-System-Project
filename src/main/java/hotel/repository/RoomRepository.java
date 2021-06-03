package hotel.repository;

import hotel.model.Room;
import hotel.model.RoomCategory;
import hotel.model.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findById(long apartmentId);

    List<Room> findAllByStatus(RoomStatus status);

    List<Room> findAllByGuestsNumberAndStatusAndCategory(RoomStatus status,
                                                         long guestsNumber,
                                                         RoomCategory category);

}
