package com.example.saebackend.data.mariadb.properties.rental;

import com.example.saebackend.data.mariadb.properties.PropertyEntity;
import com.example.saebackend.data.mariadb.properties.location.LocationEntity;
import com.example.saebackend.data.mariadb.properties.roomtype.RoomTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "rental_property")
public class RentalPropertyEntity extends PropertyEntity {
    private double rent;
    @Column(name = "rent_frequency")
    private String rentFrequency;

    public RentalPropertyEntity() {}

    public RentalPropertyEntity(String id, String type, String title, String description, LocationEntity location, List<String> images, double livingArea, double landArea, Map<RoomTypeEntity, Integer> rooms, String orientation, String energyClass, String climateClass, String view, double estimationEnergyCost, double rent, String rentFrequency) {
        super(id, type, title, description, location, images, livingArea, landArea, rooms, orientation, energyClass, climateClass, view, estimationEnergyCost);
        this.rent = rent;
        this.rentFrequency = rentFrequency;
    }

    public double getRent() {
        return rent;
    }

    public String getRentFrequency() {
        return rentFrequency;
    }
}
