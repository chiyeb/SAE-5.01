package com.example.saebackend.model.property;

import com.example.saebackend.model.id.Id;

public class RoomType {
    private final Id id;
    private final String name;

    public RoomType(Id id, String name) {
        this.id = id;
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
