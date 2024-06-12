package org.euro2024.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "rooms")
public class Room {

    @Id
    private String id;

    private String roomId;

    private String roomName;

    private String roomUrl; // This will be generated

    private String roomDescription;

    @DBRef
    private List<User> users;

    @DBRef
    private RoomRule roomRules;

    @Override
    public String toString() {
        return this.roomName;
    }
}
