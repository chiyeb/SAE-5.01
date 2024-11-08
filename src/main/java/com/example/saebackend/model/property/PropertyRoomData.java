package com.example.saebackend.model.property;

import com.example.saebackend.model.id.Id;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PropertyRoomData {
    private final HashMap<RoomType, Integer> rooms;

    @JsonCreator
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
