
package org.euro2024.service;

import org.euro2024.model.Room;
import org.euro2024.model.User;
import org.euro2024.model.RoomRule;
import org.euro2024.model.User;
import org.euro2024.repository.RoomRepository;
import org.euro2024.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public Room createRoom(Room room, String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            room.setRoomId(generateRoomId());
            room.setRoomUrl(generateRoomUrl(room.getRoomId()));
            return roomRepository.save(room);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    private String generateRoomId() {
        return UUID.randomUUID().toString();
    }

    private String generateRoomUrl(String roomId) {
        return "http://localhost:3000/rooms/" + roomId; // Adjust the URL based on your frontend URL
    }

    public Room addUserToRoom(String roomId, User user) {
        Optional<Room> optionalRoom = roomRepository.findByRoomId(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            room.getUsers().add(user);
            return roomRepository.save(room);
        } else {
            throw new RuntimeException("Room not found");
        }
    }
    public Room setRoomRules(String roomUrl, RoomRule rules) {
        Optional<Room> optionalRoom = roomRepository.findByRoomUrl(roomUrl);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            rules.setRoom(room); // Set the room reference in the rule
            room.setRoomRules(rules);
            return roomRepository.save(room);
        } else {
            throw new RuntimeException("Room not found");
        }
    }


}
