package com.example.saebackend.data.mariadb.properties.roomtype;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a room type in a database.
 */
@Entity
@Table(name = "room_type")
public class RoomTypeEntity {
    @Id
    private String name;

    public RoomTypeEntity(String name) {
        this.name = name;
    }

    public RoomTypeEntity() {}

    public String getName() {
        return name;
    }
}
