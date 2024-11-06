package com.example.saebackend.model.property.purchasable;

import com.example.saebackend.model.id.Id;
import com.example.saebackend.model.property.Property;
import com.example.saebackend.model.property.PropertyLocation;
import com.example.saebackend.model.property.PropertyRoomData;
import com.example.saebackend.model.property.PropertyType;

import java.util.ArrayList;

public class PurchasableProperty extends Property {
    private final double price;

    public PurchasableProperty(Id id, double price, PropertyType propertyType, String title, String description, PropertyLocation location, ArrayList<String> images, int livingArea, int landArea, PropertyRoomData rooms, String orientation, String energyClass, String view, String climateClass, int estimationCostEnergy, Id idOwner) {
        super(id, propertyType, title, description, location, images, livingArea, landArea, rooms, orientation, energyClass, view, climateClass, estimationCostEnergy, idOwner);
        this.price = price;
    }

    @Override
    public PurchasablePropertyReadModel getReadModel() {
        return new PurchasablePropertyReadModel(getPropertyType(), getTitle(), getPriceOrSubscriptionPrice(), getDescription(),
                getLocation(), getImages(), getLivingArea(), getLandArea(), getRooms(), getOrientation(), getEnergyClass(),
                getView(), getClimateClass(), getEstimationCostEnergy());
    }

    @Override
    public double getPriceOrSubscriptionPrice() {
        return price;
    }
}
