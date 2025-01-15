package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.properties.models.RoomCountModel;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PropertyRoomDataTest {

    @Test
    void createFromModel_withValidModel_createsPropertyRoomData() {
        List<RoomCountModel> models = List.of(
                new RoomCountModel("bedroom", 2),
                new RoomCountModel("kitchen", 1)
        );
        PropertyRoomData roomData = PropertyRoomData.createFromModel(models);

        Map<RoomType, Integer> rooms = roomData.getRooms();
        assertEquals(2, rooms.get(new RoomType("bedroom")));
        assertEquals(1, rooms.get(new RoomType("kitchen")));
    }

    @Test
    void readModel_returnsCorrectModel() {
        Map<RoomType, Integer> rooms = Map.of(
                new RoomType("bedroom"), 2,
                new RoomType("kitchen"), 1
        );
        PropertyRoomData roomData = new PropertyRoomData(rooms);
        List<RoomCountModel> models = roomData.readModel();

        assertEquals(2, models.stream().filter(model -> model.roomType().equals("bedroom")).findFirst().get().count());
        assertEquals(1, models.stream().filter(model -> model.roomType().equals("kitchen")).findFirst().get().count());
    }

    @Test
    void createFromModel_withEmptyList_createsEmptyPropertyRoomData() {
        List<RoomCountModel> models = List.of();
        PropertyRoomData roomData = PropertyRoomData.createFromModel(models);

        Map<RoomType, Integer> rooms = roomData.getRooms();
        assertEquals(0, rooms.size());
    }

    @Test
    void createFromModel_withNullList_throwsException() {
        assertThrows(NullPointerException.class, () -> PropertyRoomData.createFromModel(null));
    }
}