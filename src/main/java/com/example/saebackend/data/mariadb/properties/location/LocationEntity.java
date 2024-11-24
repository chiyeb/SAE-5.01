package com.example.saebackend.data.mariadb.properties.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a location in a database.
 */
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    private String id;

    private double latitude;

    private double longitude;

    private String address;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;

    public LocationEntity(String id, double latitude, double longitude, String address, String city, String postalCode, String country) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public LocationEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }
}
