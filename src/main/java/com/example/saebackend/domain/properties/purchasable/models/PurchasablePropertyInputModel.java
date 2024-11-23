package com.example.saebackend.domain.properties.purchasable.models;

import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.models.PropertyInputModel;
import com.example.saebackend.domain.properties.models.PropertyLocationModel;
import com.example.saebackend.domain.properties.models.RoomCountModel;
import com.example.saebackend.base.validation.ValueOfEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record PurchasablePropertyInputModel(
        @NotNull
        @ValueOfEnum(enumClass = PropertyType.class)
        String type,
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        PropertyLocationModel location,
        @NotNull
        List<String> images,
        @NotNull
        @PositiveOrZero
        double price,
        @NotNull
        @Positive
        double livingArea,
        @NotNull
        @PositiveOrZero
        double landArea,
        @NotNull
        List<RoomCountModel> rooms,
        @NotNull
        String orientation,
        @NotNull
        @ValueOfEnum(enumClass = EnergyClass.class)
        String energyClass,
        @NotNull
        @ValueOfEnum(enumClass = ClimateClass.class)
        String climateClass,
        @NotNull
        String view,
        @NotNull
        @PositiveOrZero
        double estimationCostEnergy) implements PropertyInputModel {}
