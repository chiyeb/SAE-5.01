package com.example.saebackend.domain.properties.models;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record RoomCountModel(
        @NotNull(message = "Room type is required")
        String roomType,
        @NotNull(message = "Room count is required")
        @PositiveOrZero
        int count) {}
