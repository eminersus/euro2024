package org.euro2024.model;



import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "room_rules")
public class RoomRule {

    @Id
    private String id;

    @DBRef
    private Room room;

    private int maxUserNum;

    private LocalDateTime expirationDate;

    private int scoreWinnerPoint;

    private int sideWinnerPoint;

    private boolean scoreWinnerOpen;

    @Override
    public String toString() {
        return String.format("Rule for %s", room.getRoomName());
    }
}
