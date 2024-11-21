package com.example.saebackend.domain.properties.purchasable.models;

import com.example.saebackend.domain.properties.models.PropertyInputModel;
import com.example.saebackend.domain.properties.models.PropertyLocationModel;
import com.example.saebackend.domain.properties.models.RoomCountModel;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PurchasablePropertyInputModel(

        @NotNull(message = "Type is required")
        // TODO("Implémenter une contrainte pour vérifier si le type appartient au bon enum")
        String type,
        @NotNull(message = "Title is required")
        String title,
        @NotNull(message = "Description is required")
        String description,
        @NotNull(message = "Location is required")
        PropertyLocationModel location,
        @NotNull(message = "Images are required")
        List<String> images,
        @NotNull(message = "Price is required")
        double price,
        @NotNull(message = "Living area is required")
        double livingArea,
        @NotNull(message = "Land area is required")
        double landArea,
        @NotNull(message = "Rooms are required")
        List<RoomCountModel> rooms,
        @NotNull(message = "Orientation is required")
        String orientation,

        @NotNull
        // TODO("Implémenter une contrainte pour vérifier si le type appartient au bon enum")
        String energyClass,

        @NotNull
        // TODO("Implémenter une contrainte pour vérifier si le type appartient au bon enum")
        String climateClass,
        @NotNull(message = "View is required")
        String view,
        @NotNull(message = "Estimation cost energy is required")
        double estimationCostEnergy) implements PropertyInputModel {}
