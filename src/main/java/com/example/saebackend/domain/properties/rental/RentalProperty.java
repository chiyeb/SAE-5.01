package com.example.saebackend.domain.properties.rental;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.*;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyReadModel;

import java.util.List;

public class RentalProperty extends Property {
    //    private final Id idRenter; TODO("Au lieu de stocker seulement l'id renter, on devrait stocker un objet User qui contient l'id et le nom de locataire")
    private final SubscriptionFrequency subscriptionFrequency;
    private final double subscriptionPrice;

    private RentalProperty(Id id, PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData rooms, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy, SubscriptionFrequency subscriptionFrequency, double subscriptionPrice) {
        super(id, type, title, description, location, images, livingArea, landArea, rooms, orientation, energyClass, climateClass, view, estimationCostEnergy);
//        this.idRenter = idRenter;
        this.subscriptionFrequency = subscriptionFrequency;
        this.subscriptionPrice = subscriptionPrice;

    }

    public static RentalProperty create(PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData roomData, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy, SubscriptionFrequency subscriptionFrequency, double subscriptionPrice) {
        return new RentalProperty(Id.generate(), type, title, description, location, images, livingArea, landArea, roomData, orientation, energyClass, climateClass, view, estimationCostEnergy, subscriptionFrequency, subscriptionPrice);
    }

    public static RentalProperty createFromModel(RentalPropertyInputModel model){
        return new RentalProperty(
                Id.generate(),
                PropertyType.fromString(model.type()),
                model.title(),
                model.description(),
                PropertyLocation.createFromModel(model.location()),
                model.images(),
                model.livingArea(),
                model.landArea(),
                PropertyRoomData.createFromModel(model.rooms()),
                model.orientation(),
                EnergyClass.fromString(model.energyClass()),
                ClimateClass.fromString(model.climateClass()),
                model.view(),
                model.estimationCostEnergy(),
                SubscriptionFrequency.fromString(model.subscriptionFrequency()),
                model.price()
        );
    }

    //================================================================================
    // Methods
    //================================================================================

    @Override
    public PropertyReadModel readModel() {
        return new RentalPropertyReadModel(
                getId().toString(),
                getType().toString(),
                getTitle(),
                getDescription(),
                getLocation().readModel(),
                getImages(),
                getSubscriptionPrice(),
                subscriptionFrequency.toString(),
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
        return subscriptionPrice;
    }

    //================================================================================
    // Getters
    //================================================================================

//    public Id getIdRenter() {return idRenter;}

    public SubscriptionFrequency getSubscriptionFrequency() {return subscriptionFrequency;}

    public double getSubscriptionPrice() {return subscriptionPrice;}

}
