package com.example.saebackend.domain.properties.purchasable;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.*;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyReadModel;

import java.util.List;

public class PurchasableProperty extends Property {
    private final double price;

    private PurchasableProperty(Id id, PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData roomData, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy, double price) {
        super(id, type, title, description, location, images, livingArea, landArea, roomData, orientation, energyClass, climateClass, view, estimationCostEnergy);
        this.price = price;
    }

    public static PurchasableProperty create(PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData roomData, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy, double price) {
        return new PurchasableProperty(Id.generate(), type, title, description, location, images, livingArea, landArea, roomData, orientation, energyClass, climateClass, view, estimationCostEnergy, price);
    }

    public static PurchasableProperty createFromModel(PurchasablePropertyInputModel inputModel) {
        return new PurchasableProperty(
                Id.generate(),
                PropertyType.fromString(inputModel.type()),
                inputModel.title(),
                inputModel.description(),
                PropertyLocation.createFromModel(inputModel.location()),
                inputModel.images(),
                inputModel.livingArea(),
                inputModel.landArea(),
                PropertyRoomData.createFromModel(inputModel.rooms()),
                inputModel.orientation(),
                EnergyClass.fromString(inputModel.energyClass()),
                ClimateClass.fromString(inputModel.climateClass()),
                inputModel.view(),
                inputModel.estimationCostEnergy(),
                inputModel.price()
        );
    }

    @Override
    public PropertyReadModel readModel() {
        return new PurchasablePropertyReadModel(
                getId().toString(),
                getType().toString(),
                getTitle(),
                getDescription(),
                getLocation().readModel(),
                getImages(),
                getPriceOrSubscriptionPrice(),
                getLivingArea(),
                getLandArea(),
                getRooms().readModel(),
                getOrientation(),
                getEnergyClass().toString(),
                getClimateClass().toString(),
                getView(),
                getEstimationCostEnergy()
        );
    }

    @Override
    public double getPriceOrSubscriptionPrice() {
        return price;
    }
}
