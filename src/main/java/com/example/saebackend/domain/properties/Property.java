package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.models.PropertyReadModel;

import java.util.List;

public abstract class Property {
    private final Id id;
    private final PropertyType type;
    private final String title;
    private final String description;
    private final PropertyLocation location;
    private final List<String> images;
    private final double livingArea;
    private final double landArea;
    private final PropertyRoomData rooms;
    private final String orientation;
    private final EnergyClass energyClass;
    private final ClimateClass climateClass;
    private final String view;
    private final double estimationCostEnergy;

//    private final Id idOwner; TODO(implémenter utilisateur)

    //================================================================================
    // Constructor with Id
    //================================================================================

    public Property(Id id, PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData rooms, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy) {
        this.id = id;
        this.type = type;
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
    }

    //================================================================================
    // Methods
    //================================================================================

    public abstract PropertyReadModel readModel();
    public abstract double getPriceOrSubscriptionPrice();

    //================================================================================
    // Getters
    //================================================================================

    public Id getId() {
        return id;
    }

    public PropertyType getType() {
        return type;
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

    public List<String> getImages() {
        return images;
    }

    public double getLivingArea() {
        return livingArea;
    }

    public double getLandArea() {
        return landArea;
    }

    public PropertyRoomData  getRoomsData() {return rooms;}

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

    public PropertyRoomData getRooms() {
        return rooms;
    }

    //    public Id getIdOwner() { TODO(implémenter utilisateur)
//        return idOwner;
//    }

}
