package com.example.saebackend.domain.properties.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

/**
 * Represents the count of rooms for a specific room type.
 * <p>
 * This record is used for API interactions to create {@code PropertyRoomData}
 * from JSON input and to generate JSON output from {@code PropertyRoomData}.
 * </p>
 *
 * @param roomType the name of the room type (e.g., "bedroom", "bathroom").
 *                 Must not be {@code null}.
 * @param count    the count of rooms of the given type. Must not be negative.
 */
public record RoomCountModel(
        @NotNull(message = "Room type is required")
        String roomType,
        @NotNull(message = "Room count is required")
        @PositiveOrZero
        int count
) {}
