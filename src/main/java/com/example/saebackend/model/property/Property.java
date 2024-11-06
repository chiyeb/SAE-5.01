package com.example.saebackend.model.property;

import com.example.saebackend.model.id.Id;

import java.util.ArrayList;

public abstract class Property {
    private final Id id;
    private final PropertyType propertyType;
    private final String title;
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
    private final Id idOwner;

    //================================================================================
    // Constructor
    //================================================================================

    public Property(Id id, PropertyType propertyType, String title, String description, PropertyLocation location, ArrayList<String> images, int livingArea, int landArea, PropertyRoomData rooms, String orientation, EnergyClass energyClass, String view, ClimateClass climateClass, double estimationCostEnergy, Id idOwner) {
        this.id = id;
        this.propertyType = propertyType;
        this.title = title;
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
        this.idOwner = idOwner;
    }

    //================================================================================
    // Methods
    //================================================================================

    public abstract Object getReadModel();
    public abstract double getPriceOrSubscriptionPrice();

    //================================================================================
    // Getters
    //================================================================================

    public Id getId() {
        return id;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PropertyLocation getLocation() {
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

    public Id getIdOwner() {
        return idOwner;
    }

}
