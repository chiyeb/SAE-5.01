package com.example.saebackend.data.inmemory.properties;

import com.example.saebackend.domain.id.Id;
import com.example.saebackend.domain.properties.PropertyLocation;
import com.example.saebackend.domain.properties.PropertyRoomData;
import com.example.saebackend.domain.properties.RoomType;
import com.example.saebackend.domain.properties.enums.ClimateClass;
import com.example.saebackend.domain.properties.enums.EnergyClass;
import com.example.saebackend.domain.properties.enums.PropertyType;
import com.example.saebackend.domain.properties.purchasable.PurchasableProperty;
import com.example.saebackend.domain.properties.purchasable.models.PurchasablePropertyInputModel;
import com.example.saebackend.repositories.properties.PurchasablePropertyRepository;
import com.example.saebackend.repositories.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of the PurchasablePropertyRepository interface.
 * Provides methods for managing purchasable properties stored in memory.
 */
@Repository
public class InMemoryPurchasablePropertyRepository implements PurchasablePropertyRepository {
    private final Map<String, RoomType> roomTypes = new HashMap<>();
    {
        roomTypes.put("bedroom", new RoomType("bedroom"));
        roomTypes.put("bathroom", new RoomType("bathroom"));
        roomTypes.put("kitchen", new RoomType("kitchen"));
    }
    private final List<PurchasableProperty> properties;

    public InMemoryPurchasablePropertyRepository(UserRepository userRepository) {
        final PropertyRoomData propertyRoomData = new PropertyRoomData(
                Map.of(
                        roomTypes.get("bedroom"), 2,
                        roomTypes.get("bathroom"), 1,
                        roomTypes.get("kitchen"), 1
                )
        );
        this.properties = new ArrayList<>();
        this.properties.addAll(List.of(PurchasableProperty.create(
                        PropertyType.APARTMENT,
                        "Appartement moderne en centre-ville",
                        "Un appartement spacieux avec 2 chambres, cuisine équipée et balcon donnant sur la ville.",
                        new PropertyLocation(48.866667, 2.333333,"12 rue de l'eau","Paris", "70123" ,"France"), // Location
                        List.of(
                                "https://picsum.photos/500/500",
                                "https://picsum.photos/500/500"
                        ), // Images
                        75.0, // Living area in square meters
                        0.0,  // Land area in square meters
                        propertyRoomData,
                        "Sud", // Orientation
                        EnergyClass.B, // Energy class
                        ClimateClass.A, // Climate class
                        "Vue sur la ville", // View
                        120.0, // Estimation cost of energy
                        200000.0, // Price
                        userRepository.getByMail("impulsewordpresssae@alwaysdata.net") // Owner
                ),
                PurchasableProperty.create(
                        PropertyType.HOUSE,
                        "Maison de campagne pittoresque",
                        "Charmante maison de campagne avec un grand jardin, une terrasse et vue sur les montagnes.",
                        new PropertyLocation(45.866667, 6.333333,"12 rue de l'eau","Annecy", "70123" ,"France"), // Location
                        new ArrayList<>(), // Images
                        150.0, // Living area in square meters
                        1000.0, // Land area in square meters
                        propertyRoomData,
                        "Ouest", // Orientation
                        EnergyClass.C, // Energy class
                        ClimateClass.B, // Climate class
                        "Vue sur les montagnes", // View
                        300.0, // Estimation cost of energy
                        350000.0, // Price
                        userRepository.getByMail("impulsewordpresssae@alwaysdata.net") // Owner
                ),
                PurchasableProperty.create(
                        PropertyType.APARTMENT,
                        "Studio cosy proche de la plage",
                        "Petit studio avec accès direct à la plage, parfait pour les vacances.",
                        new PropertyLocation(43.866667, 7.333333,"12 rue de l'eau","Nice", "70123" ,"France"), // Location
                        new ArrayList<>(), // Images
                        30.0, // Living area in square meters
                        0.0,  // Land area in square meters
                        propertyRoomData,
                        "Est", // Orientation
                        EnergyClass.A, // Energy class
                        ClimateClass.A, // Climate class
                        "Vue sur la mer", // View
                        80.0, // Estimation cost of energy
                        100000.0,// Price
                        userRepository.getByMail("impulsewordpresssae@alwaysdata.net") // Owner
                )));
    }

    @Override
    public PurchasableProperty create(PurchasableProperty purchasableProperty) {
        properties.add(purchasableProperty);
        return purchasableProperty;
    }

    @Override
    public PurchasableProperty getById(Id id) {
        return properties.stream()
                .filter(property -> property.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<PurchasableProperty> getAll() {
        return properties;
    }


    @Override
    public PurchasableProperty update(Id id, PurchasablePropertyInputModel updateData) {
        PurchasableProperty propertyToUpdate = getById(id);
        if (propertyToUpdate == null) return null;
        return propertyToUpdate.updateFromModel(updateData);
    }

    @Override
    public boolean deleteById(Id id) {
        return properties.removeIf(property -> property.getId().equals(id));
    }

    @Override
    public List<PurchasableProperty> getByOwnerId(Id ownerId) {
        return properties.stream()
                .filter(property -> property.getOwner().getId().equals(ownerId))
                .toList();
    }

}