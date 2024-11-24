package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.properties.models.RoomCountModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Represents the room data for a property, mapping room types to their respective counts.
 */
public class PropertyRoomData {
    private final Map<RoomType, Integer> rooms;

    public PropertyRoomData(Map<RoomType, Integer> rooms) {
        this.rooms = rooms;
    }

    /**
     * Creates a {@code PropertyRoomData} instance from a list of {@code RoomCountModel}.
     *
     * @param rooms a list of {@link RoomCountModel}, each representing a room type and its count.
     * @return a new {@code PropertyRoomData} instance with the mapped room data.
     */
    public static PropertyRoomData createFromModel(List<RoomCountModel> rooms) {
        Map<RoomType, Integer> roomMap = rooms.stream()
                .collect(Collectors.toMap(
                        roomCountModel -> new RoomType(roomCountModel.roomType()),
                        RoomCountModel::count
                ));
        return new PropertyRoomData(roomMap);
    }

    /**
     * Converts the internal room data into a list of {@code RoomCountModel}.
     *
     * @return a list of {@link RoomCountModel} representing the room data.
     */
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
