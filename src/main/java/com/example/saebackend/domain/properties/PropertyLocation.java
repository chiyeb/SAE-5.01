package com.example.saebackend.domain.properties;

import com.example.saebackend.domain.properties.models.PropertyLocationModel;

public class PropertyLocation {

    private final double latitude;
    private final double longitude;
    private final String address;
    private final String city;
    private final String postalCode;
    private final String country;

    public PropertyLocation(double latitude, double longitude, String address, String city, String postalCode, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public static PropertyLocation createFromModel(PropertyLocationModel propertyLocationModel){
        return new PropertyLocation(propertyLocationModel.latitude(), propertyLocationModel.longitude(), propertyLocationModel.address(), propertyLocationModel.city(), propertyLocationModel.postalCode(), propertyLocationModel.country());
    }

    public PropertyLocationModel readModel() {
        return new PropertyLocationModel(latitude, longitude, address, city, postalCode, country);
    }

    //================================================================================
    // Getters
    //================================================================================


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
