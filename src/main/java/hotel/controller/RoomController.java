package hotel.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import hotel.controller.dto.CreateRoomRequest;
import hotel.controller.dto.UpdateRoomRequest;
import hotel.model.Room;
import hotel.model.RoomCategory;
import hotel.model.RoomStatus;
import hotel.service.PermissionService;
import hotel.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/room")
public class RoomController {

    private final RoomService roomService;

    private final PermissionService permissionService;

    public RoomController(RoomService roomService, PermissionService permissionService) {
        this.roomService = roomService;
        this.permissionService = permissionService;
    }

    @PostMapping("/{userId}")
    public long createRoom(@PathVariable long userId, @JsonProperty @RequestBody CreateRoomRequest request) {
        permissionService.checkPermission(userId);
        return roomService.createRoom(request);
    }

    @GetMapping("/id/{id}")
    public Room findRoomsById(@PathVariable long id){
        return roomService.findRoomById(id);
    }

    @GetMapping("/{userId}")
    List<Room> findAllRooms(@PathVariable long userId) {
        permissionService.checkPermission(userId);
        return roomService.findAllRooms();
    }

    @GetMapping("/status/{userId}")
    List<Room> findRoomByStatus(@PathVariable long userId,
                                          @RequestParam RoomStatus status) {
        permissionService.checkPermission(userId);
        return roomService.findRoomsByStatus(status);
    }

    @PutMapping("/status/{roomId}/{userId}")
    public Room editRoomStatus(@PathVariable long userId, @PathVariable long roomId, @RequestParam RoomStatus status){
        permissionService.checkPermission(userId);
        return roomService.editRoomStatus(roomId, status);
    }

    @PutMapping("/info/{roomId}/{userId}")
    public Room editRoomInfo(@PathVariable long userId, @PathVariable long roomId, @RequestBody UpdateRoomRequest request){
        permissionService.checkPermission(userId);
        return roomService.editRoomInfo(roomId, request);
    }

    @GetMapping("/available/{guestsNumber}/{category}")
    List<Room> findAvailableRoomsByGuestsNumberAndCategory(@PathVariable long guestsNumber,
                                                                     @PathVariable RoomCategory category) {
        return roomService.findAvailableRoomsByGuestsNumberAndCategory(guestsNumber, category);
    }



}
