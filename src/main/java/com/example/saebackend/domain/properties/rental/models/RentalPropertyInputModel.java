package com.example.saebackend.domain.properties.rental.models;

import com.example.saebackend.domain.properties.models.PropertyInputModel;
import com.example.saebackend.domain.properties.models.PropertyLocationModel;
import com.example.saebackend.domain.properties.models.RoomCountModel;

import java.util.List;

public record RentalPropertyInputModel(String type,
                                       String title,
                                       String description,
                                       PropertyLocationModel location,
                                       List<String> images,
                                       double price,
                                       String subscriptionFrequency,
                                       double livingArea,
                                       double landArea,
                                       List<RoomCountModel> rooms,
                                       String orientation,
                                       String energyClass,
                                       String climateClass,
                                       String view,
                                       double estimationCostEnergy) implements PropertyInputModel {}
