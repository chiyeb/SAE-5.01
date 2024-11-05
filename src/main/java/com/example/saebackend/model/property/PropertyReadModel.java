package com.example.saebackend.model.property;

import java.util.ArrayList;

public class PropertyReadModel {
    private final String title;
    private final String value;
    private final String description;
    private final String localisation;
    private final ArrayList<String> images;
    private final int livingArea;
    private final int landArea;
    private final String numberOfBedrooms;
    private final String numberOfBathrooms;
    private final String orientation;
    private final String energyClass;
    private final String view;
    private final String climateClass;
    private final int estimationCostEnergy;

    public PropertyReadModel(String title, String value, String description, String localisation, ArrayList<String> images, int livingArea, int landArea, String numberOfBedrooms, String numberOfBathrooms, String orientation, String energyClass, String view, String climateClass, int estimationCostEnergy) {
        this.title = title;
        this.value = value;
        this.description = description;
        this.localisation = localisation;
        this.images = images;
        this.livingArea = livingArea;
        this.landArea = landArea;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.orientation = orientation;
        this.energyClass = energyClass;
        this.view = view;
        this.climateClass = climateClass;
        this.estimationCostEnergy = estimationCostEnergy;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }

    public String getLocalisation() {
        return localisation;
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

    public String getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public String getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

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
}
