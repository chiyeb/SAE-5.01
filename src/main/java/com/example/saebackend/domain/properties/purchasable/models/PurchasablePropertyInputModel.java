package com.example.saebackend.domain.properties.purchasable.models;

import com.example.saebackend.domain.properties.models.PropertyInputModel;
import com.example.saebackend.domain.properties.models.PropertyLocationModel;
import com.example.saebackend.domain.properties.models.RoomCountModel;

import java.util.List;

public record PurchasablePropertyInputModel(
        String type,
        String title,
        String description,
        PropertyLocationModel location,
        List<String> images,
        double price,
        double livingArea,
        double landArea,
        List<RoomCountModel> rooms,
        String orientation,
        String energyClass,
        String climateClass,
        String view,
        double estimationCostEnergy) implements PropertyInputModel {}
