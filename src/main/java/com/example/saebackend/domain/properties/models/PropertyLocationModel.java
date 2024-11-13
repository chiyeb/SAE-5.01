package com.example.saebackend.domain.properties.models;

public record PropertyLocationModel(double latitude,
                                    double longitude,
                                    String address,
                                    String city,
                                    String postalCode,
                                    String country) {}
