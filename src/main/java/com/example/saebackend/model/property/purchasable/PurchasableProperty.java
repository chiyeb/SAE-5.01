package com.example.saebackend.model.property.purchasable;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.*;
import com.example.saebackend.model.property.enums.ClimateClass;
import com.example.saebackend.model.property.enums.EnergyClass;
import com.example.saebackend.model.property.enums.PropertyType;

import java.util.ArrayList;

public class PurchasableProperty extends Property {
    private final double price;

    public PurchasableProperty(Id id, double price, PropertyType propertyType, String title, String description, PropertyLocation location, ArrayList<String> images, int livingArea, int landArea, PropertyRoomData rooms, String orientation, EnergyClass energyClass, String view, ClimateClass climateClass, double estimationCostEnergy, Id idOwner) {
        super(id, propertyType, title, description, location, images, livingArea, landArea, rooms, orientation, energyClass, view, climateClass, estimationCostEnergy, idOwner);
        this.price = price;
    }

    @Override
    public PurchasablePropertyReadModel getReadModel() {
        return new PurchasablePropertyReadModel(getPropertyType(), getTitle(), getPriceOrSubscriptionPrice(), getDescription(),
                getLocation(), getImages(), getLivingArea(), getLandArea(), getRoomsData(), getOrientation(), getEnergyClass(),
                getView(), getClimateClass(), getEstimationCostEnergy());
    }

    @Override
    public double getPriceOrSubscriptionPrice() {
        return price;
    }
}
