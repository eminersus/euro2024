package org.euro2024.repository;


import org.euro2024.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;



public interface RoomRepository extends MongoRepository<Room, String> {
    Optional<Room> findByRoomId(String roomId);
    Optional<Room> findByRoomUrl(String roomUrl);
}
