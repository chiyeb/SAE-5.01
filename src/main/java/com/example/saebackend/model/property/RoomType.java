package com.example.saebackend.model.property;

import com.example.saebackend.model.id.Id;

public class RoomType {
    private final Id id;
    private final String name;
    private final int count;

    //================================================================================
    // Constructor
    //================================================================================
    public RoomType(Id id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public RoomType(String name, int count) {
        this.id = new Id();
        this.name = name;
        this.count = count;
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

    public int getCount() {
        return count;
    }
}
