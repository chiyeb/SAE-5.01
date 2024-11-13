package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.properties.models.RoomCountModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class PropertyRoomData {
    private final Map<RoomType, Integer> rooms;

    public PropertyRoomData(Map<RoomType, Integer> rooms) {
        this.rooms = rooms;
    }

    public static PropertyRoomData createFromModel(List<RoomCountModel> rooms) {
        Map<RoomType, Integer> roomMap = rooms.stream()
                .collect(Collectors.toMap(
                        roomCountModel -> new RoomType(roomCountModel.roomType()),
                        RoomCountModel::count
                ));
        return new PropertyRoomData(roomMap);
    }

    public List<RoomCountModel> readModel() {
        return rooms.entrySet().stream()
                .map(entry -> new RoomCountModel(entry.getKey().name(), entry.getValue()))
                .toList();
    }

    //================================================================================
    // Getters
    //================================================================================

    public Map<RoomType, Integer> getRooms() {
        return rooms;
    }

}
