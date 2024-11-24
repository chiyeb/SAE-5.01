package com.example.saebackend.domain.properties.purchasable.models;

import com.example.saebackend.domain.properties.models.PropertyLocationModel;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.models.RoomCountModel;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.users.UserReadModel;

import java.util.List;

/**
 * A read-only model that represents the data of a {@link PurchasableProperty}.
 * This model is used for the representation of property details when reading or viewing
 * the property data through an API or a similar interface.
 */
public record PurchasablePropertyReadModel(
        String id,
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
        double estimationCostEnergy,
        UserReadModel owner) implements PropertyReadModel {}
