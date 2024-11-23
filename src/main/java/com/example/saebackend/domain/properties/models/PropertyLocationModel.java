package com.example.saebackend.domain.properties.models;

import jakarta.validation.constraints.NotNull;

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
        String country) {}
