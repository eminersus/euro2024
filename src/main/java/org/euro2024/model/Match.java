package org.euro2024.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "matches")
public class Match {

    @Id
    private String id;

    private String homeTeam;

    private String awayTeam;

    private String matchStatus;

    private int homeScore;

    private int awayScore;

    private LocalDateTime matchDate;

    @Override
    public String toString() {
        return String.format("%s vs %s on %s", homeTeam, awayTeam, matchDate);
    }
}
