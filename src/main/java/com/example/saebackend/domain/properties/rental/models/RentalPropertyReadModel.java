package com.example.saebackend.domain.properties.rental.models;

import com.example.saebackend.domain.properties.*;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.rental.SubscriptionFrequency;

import java.util.ArrayList;

public class RentalPropertyReadModel {
    private final String title;
    private final PropertyType propertyType;
    private final double subscriptionPrice;
    private final String description;
    private final PropertyLocation location;
    private final ArrayList<String> images;
    private final int livingArea;
    private final int landArea;
    private final PropertyRoomData rooms;
    private final String orientation;
    private final EnergyClass energyClass;
    private final String view;
    private final ClimateClass climateClass;
    private final double estimationCostEnergy;
    private final SubscriptionFrequency subscriptionFrequency;

    //================================================================================
    // Constructor
    //================================================================================

    public RentalPropertyReadModel(String title, PropertyType propertyType, double subscriptionPrice, String description,
                                   PropertyLocation location, ArrayList<String> images, int livingArea, int landArea,
                                   PropertyRoomData rooms, String orientation, EnergyClass energyClass, String view, ClimateClass climateClass,
                                   double estimationCostEnergy, SubscriptionFrequency subscriptionFrequency) {
        this.title = title;
        this.propertyType = propertyType;
        this.subscriptionPrice = subscriptionPrice;
        this.description = description;
        this.location = location;
        this.images = images;
        this.livingArea = livingArea;
        this.landArea = landArea;
        this.rooms = rooms;
        this.orientation = orientation;
        this.energyClass = energyClass;
        this.view = view;
        this.climateClass = climateClass;
        this.estimationCostEnergy = estimationCostEnergy;
        this.subscriptionFrequency = subscriptionFrequency;
    }

    public String getTitle() {
        return title;
    }
    public PropertyType getPropertyType() {return propertyType;}

    public double getSubscriptionPrice() {
        return subscriptionPrice;
    }

    public String getDescription() {
        return description;
    }

    public PropertyLocation getLocalisation() {
        return location;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public int getLivingArea() {
        return livingArea;
    }

    public int getLandArea() {
        return landArea;
    }

    public PropertyRoomData getRooms() {return rooms;}

    public String getOrientation() {
        return orientation;
    }

    public EnergyClass getEnergyClass() {
        return energyClass;
    }

    public String getView() {
        return view;
    }

    public ClimateClass getClimateClass() {
        return climateClass;
    }

    public double getEstimationCostEnergy() {
        return estimationCostEnergy;
    }

    public SubscriptionFrequency getSubscriptionFrequency() {return subscriptionFrequency;}
}
