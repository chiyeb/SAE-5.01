package com.example.saebackend.domain.properties.models;

import jakarta.validation.constraints.NotNull;

/**
 * Represents the model for the location of a property, including latitude, longitude,
 * address, city, postal code, and country.
 * <p>
 * This model is used for API serialization and deserialization, where it represents
 * the data for a property’s location that can be sent to or received from a client.
 * </p>
 *
 * @param latitude the latitude of the property.
 * @param longitude the longitude of the property.
 * @param address the address of the property.
 * @param city the city where the property is located.
 * @param postalCode the postal code of the property.
 * @param country the country where the property is located.
 */
public record PropertyLocationModel(
        @NotNull(message = "Latitude is required")
        double latitude,
        @NotNull(message = "Longitude is required")
        double longitude,
        @NotNull(message = "Address is required")
        String address,
        @NotNull(message = "City is required")
        String city,
        @NotNull(message = "Postal code is required")
        String postalCode,
        @NotNull(message = "Country is required")
        String country)
{}
