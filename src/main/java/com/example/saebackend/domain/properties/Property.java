package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.models.PropertyReadModel;

import java.util.List;

public abstract class Property {
    private final Id id;
    private PropertyType type;
    private String title;
    private String description;
    private PropertyLocation location;
    private List<String> images;
    private double livingArea;
    private double landArea;
    private PropertyRoomData rooms;
    private String orientation;
    private EnergyClass energyClass;
    private ClimateClass climateClass;
    private String view;
    private double estimationCostEnergy;

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
    public abstract void setPriceOrSubscriptionPrice(double priceOrSubscriptionPrice);

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

    public PropertyRoomData getRooms() {
        return rooms;
    }

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

    public void setType(PropertyType type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(PropertyLocation location) {
        this.location = location;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setLivingArea(double livingArea) {
        this.livingArea = livingArea;
    }

    public void setLandArea(double landArea) {
        this.landArea = landArea;
    }

    public void setRooms(PropertyRoomData rooms) {
        this.rooms = rooms;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setEnergyClass(EnergyClass energyClass) {
        this.energyClass = energyClass;
    }

    public void setClimateClass(ClimateClass climateClass) {
        this.climateClass = climateClass;
    }

    public void setView(String view) {
        this.view = view;
    }

    public void setEstimationCostEnergy(double estimationCostEnergy) {
        this.estimationCostEnergy = estimationCostEnergy;
    }

    //    public Id getIdOwner() { TODO(implémenter utilisateur)
//        return idOwner;
//    }

}
