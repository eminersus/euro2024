package org.euro2024.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }
}
