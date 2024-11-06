package com.example.saebackend.model.property;

import com.example.saebackend.model.id.Id;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.HashSet;

public class PropertyRoomData {
    private final HashMap<RoomType, Id> rooms;

    public PropertyRoomData(HashMap<RoomType, Id> rooms) {
        this.rooms = rooms;
    }

    //================================================================================
    // Getters
    //================================================================================

    public HashMap<RoomType, Id> getRooms() {
        return rooms;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
