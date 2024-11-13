package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.id.Id;

public class RoomType {
    private final Id id;
    private final String name;

    //================================================================================
    // Constructor
    //================================================================================
    public RoomType(Id id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoomType(String name) {
        this.id = new Id();
        this.name = name;
    }

    //================================================================================
    // Getters
    //================================================================================

    public Id getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}