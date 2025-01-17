package br.com.ada.reservala.controller;

import br.com.ada.reservala.domain.Room;
import br.com.ada.reservala.dto.RoomDtoRequest;
import br.com.ada.reservala.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;


    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody @Valid RoomDtoRequest roomDto) {
        Room room = roomDto.convertToRoom();
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping
    public ResponseEntity<List<Room>> readRoom() {
        return ResponseEntity.ok(roomService.readRoom());
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody @Valid RoomDtoRequest roomDto){
        Room room = roomDto.convertToRoom();
        return ResponseEntity.ok(roomService.updateRoom(room));
    }

    @DeleteMapping("/{roomNumber}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("roomNumber") Integer roomNumber){
        boolean deleted = roomService.deleteRoom(roomNumber);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/all")
    public ResponseEntity<Integer> deleteAllRooms() {
        int deletedCount = roomService.deleteAllRooms();
        return ResponseEntity.ok(deletedCount);
    }

    public ResponseEntity<Double> getOcupation(){
        return ResponseEntity.ok(roomService.getOcupation());
    }

    public ResponseEntity<Double> getRevenue(){
        return ResponseEntity.ok(roomService.getRevenue());
    }

}
