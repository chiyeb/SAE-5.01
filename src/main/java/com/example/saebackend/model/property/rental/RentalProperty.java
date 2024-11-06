package com.example.saebackend.model.property.rental;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.*;

import java.util.ArrayList;

public class RentalProperty extends Property {
    private final Id idRenter;
    private final SubscriptionFrequency subscriptionFrequency;
    private final double subscriptionPrice;

    public RentalProperty(Id id, PropertyType propertyType, String title, String description, PropertyLocation location,
                          ArrayList<String> images, int livingArea, int landArea, PropertyRoomData rooms, String orientation,
                          EnergyClass energyClass, String view, ClimateClass climateClass, double estimationCostEnergy, Id idOwner,
                          Id idRenter, SubscriptionFrequency subscriptionFrequency, double subscriptionPrice) {
        super(id, propertyType, title, description, location, images, livingArea, landArea, rooms, orientation,
                energyClass, view, climateClass, estimationCostEnergy, idOwner);
        this.idRenter = idRenter;
        this.subscriptionFrequency = subscriptionFrequency;
        this.subscriptionPrice = subscriptionPrice;

    }

    //================================================================================
    // Methods
    //================================================================================

    @Override
    public RentalPropertyReadModel getReadModel() {
        return new RentalPropertyReadModel(getTitle(), getPropertyType(), getSubscriptionPrice(), getDescription(),
                getLocation(), getImages(), getLivingArea(), getLandArea(), getRooms(), getOrientation(), getEnergyClass(),
                getView(), getClimateClass(), getEstimationCostEnergy(), subscriptionFrequency);
    }

    @Override
    public double getPriceOrSubscriptionPrice() {
        return subscriptionPrice;
    }

    //================================================================================
    // Getters
    //================================================================================

    public Id getIdRenter() {return idRenter;}

    public SubscriptionFrequency getSubscriptionFrequency() {return subscriptionFrequency;}

    public double getSubscriptionPrice() {return subscriptionPrice;}

}
