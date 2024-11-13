package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.properties.RoomType;
import com.google.gson.Gson;

import java.util.HashMap;


public class PropertyRoomData {
    private final HashMap<RoomType, Integer> rooms;

    public PropertyRoomData(HashMap<RoomType, Integer> rooms) {
        this.rooms = rooms;
    }

    //================================================================================
    // Getters
    //================================================================================

    public HashMap<RoomType, Integer> getRooms() {
        return rooms;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
