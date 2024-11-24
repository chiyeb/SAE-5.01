package com.example.saebackend.domain.properties.rental;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.*;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.models.PropertyReadModel;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyInputModel;
import com.example.saebackend.domain.properties.rental.models.RentalPropertyReadModel;
import com.example.saebackend.domain.users.UserModel;

import java.util.List;

/**
 * A class representing a rental property that can be rented by a user. This class extends the {@link Property} class
 * and adds additional functionality specific to rental properties, such as subscription frequency and subscription price.
 */
public class RentalProperty extends Property {
    //    private final Id idRenter; TODO("Au lieu de stocker seulement l'id renter, on devrait stocker un objet User qui contient l'id et le nom de locataire")
    private SubscriptionFrequency subscriptionFrequency;
    private double subscriptionPrice;

    public RentalProperty(Id id, PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData rooms, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy, SubscriptionFrequency subscriptionFrequency, double subscriptionPrice, UserModel user) {
        super(id, type, title, description, location, images, livingArea, landArea, rooms, orientation, energyClass, climateClass, view, estimationCostEnergy, user);
//        this.idRenter = idRenter;
        this.subscriptionFrequency = subscriptionFrequency;
        this.subscriptionPrice = subscriptionPrice;

    }


    public static RentalProperty create(PropertyType type, String title, String description, PropertyLocation location, List<String> images, double livingArea, double landArea, PropertyRoomData roomData, String orientation, EnergyClass energyClass, ClimateClass climateClass, String view, double estimationCostEnergy, SubscriptionFrequency subscriptionFrequency, double subscriptionPrice, UserModel user) {
        return new RentalProperty(Id.generate(), type, title, description, location, images, livingArea, landArea, roomData, orientation, energyClass, climateClass, view, estimationCostEnergy, subscriptionFrequency, subscriptionPrice, user);
    }

    /**
     * Creates a new {@code RentalProperty} from the given input model and user.
     *
     * @param model the model containing the property data.
     * @param user       the owner of the property.
     * @return a new {@code PurchasableProperty}.
     */
    public static RentalProperty createFromModel(RentalPropertyInputModel model, UserModel user){
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
                model.price(),
                user
        );
    }

    //================================================================================
    // Methods
    //================================================================================

    /**
     * Updates the property with the data from the given input model.
     *
     * @param model the model containing the updated property data.
     * @return the updated property.
     */
    public RentalProperty updateFromModel(RentalPropertyInputModel model) {
        setType(PropertyType.fromString(model.type()));
        setTitle(model.title());
        setDescription(model.description());
        setLocation(PropertyLocation.createFromModel(model.location()));
        setImages(model.images());
        setLivingArea(model.livingArea());
        setLandArea(model.landArea());
        setRooms(PropertyRoomData.createFromModel(model.rooms()));
        setOrientation(model.orientation());
        setEnergyClass(EnergyClass.fromString(model.energyClass()));
        setClimateClass(ClimateClass.fromString(model.climateClass()));
        setView(model.view());
        setEstimationCostEnergy(model.estimationCostEnergy());
        setPriceOrSubscriptionPrice(model.price());
        setSubscriptionFrequency(SubscriptionFrequency.fromString(model.subscriptionFrequency()));
        return this;
    }

    @Override
    public PropertyReadModel readModel() {
        return new RentalPropertyReadModel(
                getId().toString(),
                getType().toString(),
                getTitle(),
                getDescription(),
                getLocation().readModel(),
                getImages(),
                getPriceOrSubscriptionPrice(),
                getSubscriptionFrequency().toString(),
                getLivingArea(),
                getLandArea(),
                getRooms().readModel(),
                getOrientation(),
                getEnergyClass().toString(),
                getClimateClass().toString(),
                getView(),
                getEstimationCostEnergy(),
                getOwner().readModel()
        );
    }

    @Override
    public double getPriceOrSubscriptionPrice() {
        return subscriptionPrice;
    }

    @Override
    public void setPriceOrSubscriptionPrice(double priceOrSubscriptionPrice) {
        this.subscriptionPrice = priceOrSubscriptionPrice;
    }

    //================================================================================
    // Getters
    //================================================================================

//    public Id getIdRenter() {return idRenter;}

    public SubscriptionFrequency getSubscriptionFrequency() {return subscriptionFrequency;}

    public void setSubscriptionFrequency(SubscriptionFrequency subscriptionFrequency) {
        this.subscriptionFrequency = subscriptionFrequency;
    }
}
