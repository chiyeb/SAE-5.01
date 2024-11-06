package com.example.saebackend.model.property.rental;

import com.example.saebackend.model.property.PropertyLocation;
import com.example.saebackend.model.property.PropertyRoomData;
import com.example.saebackend.model.property.PropertyType;

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
    private final String energyClass;
    private final String view;
    private final String climateClass;
    private final int estimationCostEnergy;
    private final SubscriptionFrequency subscriptionFrequency;

    //================================================================================
    // Constructor
    //================================================================================

    public RentalPropertyReadModel(String title, PropertyType propertyType, double subscriptionPrice, String description,
                                   PropertyLocation location, ArrayList<String> images, int livingArea, int landArea,
                                   PropertyRoomData rooms, String orientation, String energyClass, String view, String climateClass,
                                   int estimationCostEnergy, SubscriptionFrequency subscriptionFrequency) {
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

    public String getEnergyClass() {
        return energyClass;
    }

    public String getView() {
        return view;
    }

    public String getClimateClass() {
        return climateClass;
    }

    public int getEstimationCostEnergy() {
        return estimationCostEnergy;
    }

    public SubscriptionFrequency getSubscriptionFrequency() {return subscriptionFrequency;}
}
