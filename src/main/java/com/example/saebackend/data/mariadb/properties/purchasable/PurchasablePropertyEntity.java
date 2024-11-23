package com.example.saebackend.data.mariadb.properties.purchasable;

import com.example.saebackend.data.mariadb.properties.PropertyEntity;
import com.example.saebackend.data.mariadb.properties.location.LocationEntity;
import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeEntity;
import com.example.saebackend.data.mariadb.users.UserModelEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "purchasable_property")
public class PurchasablePropertyEntity extends PropertyEntity {
    private double price;

    public PurchasablePropertyEntity() {}

    public PurchasablePropertyEntity(String id, UserModelEntity owner, String type, String title, String description, LocationEntity location, List<String> images, double livingArea, double landArea, Map<RoomTypeEntity, Integer> rooms, String orientation, String energyClass, String climateClass, String view, double estimationEnergyCost, double price) {
        super(id, owner, type, title, description, location, images, livingArea, landArea, rooms, orientation, energyClass, climateClass, view, estimationEnergyCost);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
