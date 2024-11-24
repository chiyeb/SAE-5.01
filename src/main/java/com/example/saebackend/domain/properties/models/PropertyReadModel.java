package com.example.saebackend.domain.properties.models;

import com.example.saebackend.domain.users.UserReadModel;

import java.util.List;

/**
 * Represents a read model for a property. This model is used for the data that
 * is exposed through an API or a similar interface.
 */
public interface PropertyReadModel {
    String id();
    String type();
    String title();
    String description();
    PropertyLocationModel location();
    List<String> images();
    double price();
    double livingArea();
    double landArea();
    List<RoomCountModel> rooms();
    String orientation();
    String energyClass();
    String climateClass();
    String view();
    double estimationCostEnergy();
    UserReadModel owner();
}
